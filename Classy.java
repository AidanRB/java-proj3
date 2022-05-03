import java.util.Scanner;

public class Classy {
    public static void main(String[] args) {
        Library lib = new Library("library.dat");

        // menu with options to add, remove, and list playlists,
        // show unlicensed artists, and quit
        int choice = -1;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("\n1. Add a new playlist");
            System.out.println("2. Remove a playlist");
            System.out.println("3. Open a playlist");
            System.out.println("4. Show all playlists");
            System.out.println("9. Show unlicensed artists");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            // get an integer from the user
            String line = scan.nextLine();
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                choice = -1;
            }

            switch (choice) {
                case 1:
                    addPlaylist(lib, scan);
                    break;

                case 2:
                    removePlaylist(lib, scan);
                    break;

                case 3:
                    openPlaylist(lib, scan);
                    break;

                case 4:
                    lib.showPlaylists();
                    break;

                case 9:
                    lib.printUnlicensedCreators();
                    break;

                case 0:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.err.println("Invalid choice.");
            }
        } while (choice != 0);

        lib.save("library.dat");

        scan.close();
    }

    private static void addPlaylist(Library lib, Scanner scan) {
        System.out.print("Enter the playlist title: ");
        String title = scan.nextLine();
        try {
            lib.addPlaylist(title);
            System.out.println("Playlist added.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removePlaylist(Library lib, Scanner scan) {
        System.out.print("Enter the playlist title: ");
        String title = scan.nextLine();
        try {
            lib.removePlaylist(title);
            System.out.println("Playlist removed.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void openPlaylist(Library lib, Scanner scan) {
        System.out.print("Enter the playlist title: ");
        String title = scan.nextLine();
        try {
            Playlist p = lib.openPlaylist(title);
            // menu with options to add, remove, sort, show, play, and quit
            int choice = -1;
            do {
                System.out.println("\n1. Add track");
                System.out.println("2. Remove track");
                System.out.println("3. Sort songs by title");
                System.out.println("4. Sort songs by artist");
                System.out.println("5. Sort playlist by song length");
                System.out.println("6. Show playlist");
                System.out.println("7. Play playlist");
                System.out.println("0. Return to main menu");
                System.out.print("Enter your choice: ");

                // get an integer from the user
                String line = scan.nextLine();
                try {
                    choice = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    choice = -1;
                }

                switch (choice) {
                    case 1:
                        addSong(p, scan);
                        break;

                    case 2:
                        removeSong(p, scan);
                        break;

                    case 3:
                        p.sortByTitle();
                        p.show();
                        break;

                    case 4:
                        p.sortByCreator();
                        p.show();
                        break;

                    case 5:
                        p.sortByLength();
                        p.show();
                        break;

                    case 6:
                        p.show();
                        break;

                    case 7:
                        p.play();
                        break;

                    case 0:
                        break;

                    default:
                        System.err.println("Invalid choice.");
                }
            } while (choice != 0);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // add a song to a playlist
    private static void addSong(Playlist p, Scanner scan) {
        System.out.print("Enter the song title: ");
        String title = scan.nextLine();
        System.out.print("Enter the song artist: ");
        String artist = scan.nextLine();

        // get length with validation
        int length = -1;
        while (length < 0) {
            System.out.print("Enter the song length (in seconds): ");
            String line = scan.nextLine();
            try {
                length = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.err.println("Invalid length.");
            }
        }

        try {
            p.add(new Track(title, artist, length));
            System.out.println("Track added.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    // remove a song from a playlist
    private static void removeSong(Playlist p, Scanner scan) {
        System.out.println("Current tracks:");
        p.show();

        // get an integer from the user
        int choice = -1;
        do {
            System.out.print("Choose a song to remove: ");
            String line = scan.nextLine();
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.err.println("Invalid choice.");
            }
        } while (choice < 0 || choice >= p.length());

        try {
            p.remove(choice);
            System.out.println("Track removed.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return;
    }
}
