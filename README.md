# Educational project Jet Notes

This is an educational project based on chapters 5 - 8 of the book "Jetpack Compose by Tutorials".

**Jet Notes** contains four main components: a **Notes** screen, a **Save Note** screen, a **Trash** screen and an app drawer.

The Notes screen displays the list of created notes. From here, the user can open an existing note, create a new one or open the app drawer.

The Save Note screen has two modes: an edit mode and a create a new note mode. When the user clicks on a note in the Notes screen, the Save Note screen will open in edit mode. The user can then edit the note or simply move it to the Trash screen by clicking a trash icon on the app bar.

To create a new note, the user taps on the Floating Action Button (FAB) available in the Notes screen. That opens the Save Note screen in the mode for creating a new note.

There are two types of notes: regular notes and checkable notes. Checkable notes are notes that the user can mark — or check — as done. The user can make any note checkable by using a switch component in the Save Note screen. In the Notes screen, checkable notes have a checkbox to mark the note as done.

Tapping the navigation icon on the app bar or swiping from the left border of the screen opens the app drawer. The app drawer switches between the Notes and the Trash screens. Using the drawer, a user can also change the app’s theme from light to dark.

In the Trash screen, the user can switch between regular and checkable notes using two tabs. The user can select notes and restore them or delete them permanently.


# Copyright
```text
/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
```