# Playlist Project
Most of the credit for this project will be based on the design and structure of your program and your use of course topics. Also, your program should be **robust to bad user input**.

Topics not covered in this course should not be used in this project.

Feel free to **reuse code you have already written, or example code**, but be sure to remove portions unrelated to the current assignment, and to update variable names, method names, etc.

**Use comments** to clarify what your code is trying to do.  No partial credit will be given for non-working code that is not commented.

---

A playlist can be used to hold music tracks which can be added, removed, and rearranged by title or creator or length. We can show the tracks in the list with all their information or play them all (playing can just print a statement). Each playlist should have a title.

When the program starts, any previously saved playlists are automatically opened from file(s) and listed for the user, showing their title and total length. A user can create a new playlist or open an existing playlist (by name), or delete an old playlsit. If they open a playlist, they can edit or play this current playlist, and when they finish with the current playlist any changes they made remain, and they can choose another to work with. When the program ends, all (non-deleted) playlists are saved in their current state for later runs of the program. If the user tries to create a new playlist with the same name as another, make them try again -- we won't allow two playlists with the same name.

Certain media creators have refused to give us a license to store their tracks. Maintain a list of the names of several such artists, which can be shown to the user if they want. Users can add these creators' tracks to a playlist and play them while the program runs, but when we go to save a playlist, we need to strip out any work by those creators before saving, and show a message that this has happened. (This could result in an empty playlist, but we can't help that).

If the user has put a playlist in order by, say, artist, but then adds another three songs, those songs can just be added at the end; you don't have to put them in the same order.

> hint: in general, separate your code so there are classes at the front end: ones that talk to the user (mostly a main, though you might have more than one) and classes at the back end: classes representing components of the situation, which do not talk directly to the user