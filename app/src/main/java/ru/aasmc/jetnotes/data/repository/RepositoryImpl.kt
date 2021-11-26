package ru.aasmc.jetnotes.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.aasmc.jetnotes.data.database.dao.ColorDao
import ru.aasmc.jetnotes.data.database.dao.NoteDao
import ru.aasmc.jetnotes.data.database.dbmapper.DbMapper
import ru.aasmc.jetnotes.data.database.model.ColorDbModel
import ru.aasmc.jetnotes.data.database.model.NoteDbModel
import ru.aasmc.jetnotes.domain.model.ColorModel
import ru.aasmc.jetnotes.domain.model.NoteModel

@DelicateCoroutinesApi
class RepositoryImpl(
    private val noteDao: NoteDao,
    private val colorDao: ColorDao,
    private val dbMapper: DbMapper
) : Repository {

    private val notesNotInTrashLiveData: MutableLiveData<List<NoteModel>> by lazy {
        MutableLiveData<List<NoteModel>>()
    }

    private val notesInTrashLiveData: MutableLiveData<List<NoteModel>> by lazy {
        MutableLiveData<List<NoteModel>>()
    }

    init {
        initDatabase(this::updateNotesLiveData)
    }

    @DelicateCoroutinesApi
    private fun initDatabase(postInitAction: () -> Unit) {
        GlobalScope.launch {
            val colors = ColorDbModel.DEFAULT_COLORS.toTypedArray()
            val dbColors = colorDao.getAllSync()
            if (dbColors.isNullOrEmpty()) {
                colorDao.insertAll(*colors)
            }
            // prepopulate notes
            val notes = NoteDbModel.DEFAULT_NOTES.toTypedArray()
            val dbNotes = noteDao.getAllSync()
            if (dbNotes.isNullOrEmpty()) {
                noteDao.insertAll(*notes)
            }
            postInitAction.invoke()
        }
    }


    override fun getAllNotesNotInTrash(): LiveData<List<NoteModel>> = notesNotInTrashLiveData

    override fun getAllNotesInTrash(): LiveData<List<NoteModel>> = notesInTrashLiveData

    private fun getAllNotesDependingOnTrashStateSync(inTrash: Boolean): List<NoteModel> {
        val colorDbModels: Map<Long, ColorDbModel> =
            colorDao.getAllSync().map { it.id to it }.toMap()

        val dbNotesNotInTrash: List<NoteDbModel> =
            noteDao.getAllSync().filter { it.isInTrash == inTrash }
        return dbMapper.mapNotes(dbNotesNotInTrash, colorDbModels)
    }

    override fun getNote(id: Long): LiveData<NoteModel> =
        Transformations.map(noteDao.findById(id)) {
            val colorDbModel = colorDao.findByIdSync(it.colorId)
            dbMapper.mapNote(it, colorDbModel)
        }

    override fun insertNote(note: NoteModel) {
        noteDao.insert(dbMapper.mapDbMote(note))
        updateNotesLiveData()
    }

    override fun deleteNote(id: Long) {
        noteDao.delete(id)
        updateNotesLiveData()
    }

    override fun deleteNotes(noteIds: List<Long>) {
        noteDao.delete(noteIds)
        updateNotesLiveData()
    }

    override fun moveNoteToTrash(noteId: Long) {
        val dbNote = noteDao.findByIdSync(noteId)
        val newDbNote = dbNote.copy(isInTrash = true)
        noteDao.insert(newDbNote)
        updateNotesLiveData()
    }

    override fun restoreNotesFromTrash(noteIds: List<Long>) {
        val dbNotesInTrash = noteDao.getNotesByIdsSync(noteIds)
        dbNotesInTrash.forEach {
            val newDbNote = it.copy(isInTrash = false)
            noteDao.insert(newDbNote)
        }
        updateNotesLiveData()
    }

    override fun getAllColors(): LiveData<List<ColorModel>> =
        Transformations.map(colorDao.getAll()) {
            dbMapper.mapColors(it)
        }

    override fun getAllColorsSync(): List<ColorModel> =
        dbMapper.mapColors(colorDao.getAllSync())

    override fun getColor(id: Long): LiveData<ColorModel> =
        Transformations.map(colorDao.findById(id)) {
            dbMapper.mapColor(it)
        }

    override fun getColorSync(id: Long): ColorModel =
        dbMapper.mapColor(colorDao.findByIdSync(id))

    private fun updateNotesLiveData() {
        notesNotInTrashLiveData.postValue(getAllNotesDependingOnTrashStateSync(false))
        val newNotesInTrashLiveData = getAllNotesDependingOnTrashStateSync(true)
        notesInTrashLiveData.postValue(newNotesInTrashLiveData)
    }
}






















