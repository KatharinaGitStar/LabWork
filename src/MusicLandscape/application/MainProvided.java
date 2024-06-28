// **************************************************
//
//       git.rev = ${gitrev}
//  git.revision = ${gitrevision}
//         stage = ${stage}
//
// ***************************************************
package MusicLandscape.application;

import java.util.*;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import MusicLandscape.container.MyTrackContainer;
import MusicLandscape.entities.Track;
import MusicLandscape.util.MyFormatter;
import MusicLandscape.entities.Artist;
import MusicLandscape.util.MyMatcher;
import MusicLandscape.util.comparators.DurationComparator;
import MusicLandscape.util.comparators.PerformerComparator;
import MusicLandscape.util.comparators.TitleComparator;
import MusicLandscape.util.comparators.WriterComparator;
import MusicLandscape.util.comparators.YearComparator;
import MusicLandscape.util.formatters.CSVTrackFormatter;
import MusicLandscape.util.formatters.LongTrackFormatter;
import MusicLandscape.util.matcher.DurationMatcher;
import MusicLandscape.util.matcher.TitleMatcher;
import MusicLandscape.util.io.MyReader;
import MusicLandscape.util.io.MyWriter;
import MusicLandscape.util.io.MyTrackCSVReader;
import MusicLandscape.util.formatters.ShortTrackFormatter;

/**
 *
 * @author TeM
 * @version ${gitrev}
 * @Stage ${stage}
 *
 */
public class MainProvided {

	private MyTrackContainer db = new MyTrackContainer();
	private List<Comparator<Track>> comparators = new LinkedList<Comparator<Track>>();
	private List<MyFormatter<Track>> formatters = new LinkedList<MyFormatter<Track>>();
	private List<MyMatcher<Track>> matchers = new LinkedList<MyMatcher<Track>>();

	private Comparator<Track> theComp;
	private boolean asc = true;

	private MyFormatter<Track> theFormat;
	private MyMatcher<Track> placeboMatcher = new TitleMatcher("");
	private Menu menu = new Menu();

	{
		comparators.add(theComp = new TitleComparator());
		comparators.add(new DurationComparator());
		comparators.add(new WriterComparator());
		comparators.add(new PerformerComparator());
		comparators.add(new YearComparator());

		matchers.add(placeboMatcher);
		matchers.add(new DurationMatcher());

		formatters.add(theFormat = new LongTrackFormatter());
		formatters.add(new ShortTrackFormatter());
		formatters.add(new CSVTrackFormatter());
	}

	private static final String WELCOME_TEXT = "Welcome to the FinalTrackDataBase";
	private static final String GOOD_BYE_TEXT = "Thank you for using FinalTrackDataBase";

	private static abstract class MenuItem {
		String text;
		static int nextID = 0;
		final int id = nextID++;

		abstract void execute();

		MenuItem(String s) {
			text = s;
		}

		public String toString() {
			return id + "\t" + text;
		}
	}

	private class Menu {
		private MenuItem[] menu = {
				new MenuItem("show menu") {
					void execute() {
						display_menu();
					}
				},
				new MenuItem("display selection") {
					void execute() {
						System.out.printf("displaying selection:\n");
						MainProvided.this.display(db);
					}
				},
				new MenuItem("edit") {
					void execute() {
						editTrack();
					}
				},
				new MenuItem("filter") {
					void execute() {
						filterTracks();
					}
				},
				new MenuItem("reset") {
					void execute() {
						resetSelection();
					}
				},
				new MenuItem("remove selection") {
					void execute() {
						removeSelection();
					}
				},
				new MenuItem("add") {
					void execute() {
						addTrack();
					}
				},
				new MenuItem("save selection") {
					void execute() {
						saveSelection();
					}
				},
				new MenuItem("load") {
					void execute() {
						loadTracks();
					}
				},
				new MenuItem("reverse sorting order") {
					void execute() {
						reverseSortingOrder();
					}
				},
				new MenuItem("select sorting") {
					void execute() {
						selectSorting();
					}
				},
				new MenuItem("select formatting") {
					void execute() {
						selectFormatting();
					}
				},
				new MenuItem("export to xml"){
					void execute() {
						export_to_xml();
					}
				},
				new MenuItem("exit") {
					void execute() {
						System.out.println(GOOD_BYE_TEXT);
						System.exit(0);
					}
				}
		};

		public void display(MyTrackContainer db) {
			if(db.size() == 0){
				System.out.print("no records stored.\n");
				return;
			}
			if(db.selection().length == 0){
				System.out.print("selection empty.\n");
				return;
			}

			System.out.println('\n' + theFormat.header());
			System.out.println(theFormat.topSeparator());
			for (Track tt : db.selection()) {
				System.out.println(theFormat.format(tt));
			}
			System.out.println();

			System.out.printf("%d out of %d records selected\n", db.selection().length, db.size());
			//display_menu();
		}

		public boolean execute(int input) {
			for (MenuItem m : menu) {
				if (m != null && m.id == input) {
					m.execute();
					return true;
				}
			}
			return false;
		}
	}

	void display_menu() {
		for (MenuItem m : menu.menu) {
			System.out.println(m);
		}
	}

	public void go() {
		System.out.println(WELCOME_TEXT);
		try (Scanner sc = new Scanner(System.in)) {
			menu.execute(0);
			while (true) {
				System.out.print(": ");
				if (sc.hasNextLine()) {
					String inputLine = sc.nextLine();
					try {
						int input = Integer.parseInt(inputLine);
						if (menu.execute(input)) {
							continue;
						}
						System.out.print("exit? (1=yes)");
						if (sc.hasNextLine()) {
							String exitLine = sc.nextLine();
							if (Integer.parseInt(exitLine) == 1) {
								break;
							}
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input. Please enter a number.");
					}
				} else {
					System.out.println("No input detected. Exiting program.");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MainProvided().go();
	}

	public void display(MyTrackContainer db) {
		if (db.size() == 0) {
			System.out.print("no records stored.\n");
			return;
		}
		if (db.selection().length == 0) {
			System.out.print("selection empty.\n");
			return;
		}

		System.out.println('\n' + theFormat.header());
		System.out.println(theFormat.topSeparator());
		for (Track tt : db.selection())
			System.out.println(theFormat.format(tt));
		System.out.println();

		System.out.printf("%d out of %d records selected\n", db.selection().length, db.size());
	}

	private void addTrack() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter title: ");
		String title = scanner.nextLine();
		System.out.print("Enter duration (seconds): ");
		int duration = Integer.parseInt(scanner.nextLine());
		System.out.print("Enter writer: ");
		String writer = scanner.nextLine();
		System.out.print("Enter performer: ");
		String performer = scanner.nextLine();
		System.out.print("Enter year: ");
		int year = Integer.parseInt(scanner.nextLine());

		Track newTrack = new Track(title, duration, writer, performer, year);
		db.add(newTrack);
		System.out.println("Track added successfully.");
	}

	private void editTrack() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the index of the track to edit: ");
		int index = Integer.parseInt(scanner.nextLine());
		if (index < 0 || index >= db.size()) {
			System.out.println("Invalid index.");
			return;
		}

		Track track = db.get(index);
		System.out.println("Editing track: " + track.getTitle());

		System.out.print("Enter new title (leave blank to keep current): ");
		String title = scanner.nextLine();
		if (!title.isEmpty()) {
			track.setTitle(title);
		}

		System.out.print("Enter new duration (seconds, leave blank to keep current): ");
		String durationStr = scanner.nextLine();
		if (!durationStr.isEmpty()) {
			int duration = Integer.parseInt(durationStr);
			track.setDuration(duration);
		}

		System.out.print("Enter new writer (leave blank to keep current): ");
		String writerName = scanner.nextLine();
		if (!writerName.isEmpty()) {
			Artist writer = new Artist(writerName);
			track.setWriter(writer);
		}

		System.out.print("Enter new performer (leave blank to keep current): ");
		String performerName = scanner.nextLine();
		if (!performerName.isEmpty()) {
			Artist performer = new Artist(performerName);
			track.setPerformer(performer);
		}

		System.out.print("Enter new year (leave blank to keep current): ");
		String yearStr = scanner.nextLine();
		if (!yearStr.isEmpty()) {
			int year = Integer.parseInt(yearStr);
			track.setYear(year);
		}

		System.out.println("Track edited successfully.");
	}

	private void filterTracks() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Available filters:");
		System.out.println("1: Title starts with");
		System.out.println("2: Duration in range");

		// TODO: Add more filter options as needed

		System.out.print("Select filter: ");
		int filterOption = Integer.parseInt(scanner.nextLine());

		switch (filterOption) {
			case 1:
				System.out.print("Enter title pattern: ");
				String pattern = scanner.nextLine();
				MyMatcher<Track> titleMatcher = new TitleMatcher(pattern);
				db.filter(titleMatcher);
				break;
			case 2:
				System.out.print("Enter minimum duration: ");
				int minDuration = Integer.parseInt(scanner.nextLine());
				System.out.print("Enter maximum duration: ");
				int maxDuration = Integer.parseInt(scanner.nextLine());
				MyMatcher<Track> durationMatcher = new DurationMatcher(minDuration + " " + maxDuration);
				db.filter(durationMatcher);
				break;
			// TODO: Add more cases for additional filters
			default:
				System.out.println("Invalid filter option.");
				return;
		}

		System.out.println("Filter applied.");
	}

	private void resetSelection() {
		db.resetSelection();
		System.out.println("Selection reset.");
	}

	private void removeSelection() {
		db.removeSelection();
		System.out.println("Selection removed.");
	}

	private void saveSelection() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the target file name: ");
		String fileName = scanner.nextLine();

		// Assuming writing to CSV format directly here
		try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
			writer.println("Title,Writer,Performer,Duration,Year");
			for (Track track : db.selection()) {
				writer.printf("%s,%s,%s,%d,%d\n",
						track.getTitle(),
						track.getWriter().getName(),
						track.getPerformer().getName(),
						track.getDuration(),
						track.getYear());
			}
			System.out.println("Tracks saved to " + fileName);
		} catch (IOException e) {
			System.out.println("Error saving tracks: " + e.getMessage());
		}
	}

	private void loadTracks() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the file name including full path: ");
		String fileName = scanner.nextLine();

		// Assuming loading from CSV format directly here
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			boolean isFirstLine = true;
			while ((line = reader.readLine()) != null) {
				if (isFirstLine) {
					// Skip the header line
					isFirstLine = false;
					continue;
				}
				String[] parts = line.split(",");
				if (parts.length == 5) {
					try {
						String title = parts[0];
						String writer = parts[1];
						String performer = parts[2];
						int duration = Integer.parseInt(parts[3]);
						int year = Integer.parseInt(parts[4]);
						Track track = new Track(title, duration, writer, performer, year);
						db.add(track);
						System.out.println("Added track: " + track);
					} catch (NumberFormatException e) {
						System.out.println("Error parsing line: " + line);
						System.out.println("Skipping invalid entry.");
					}
				} else {
					System.out.println("Invalid line format: " + line);
					System.out.println("Skipping invalid entry.");
				}
			}
			System.out.println("Tracks loaded from " + fileName);
		} catch (IOException e) {
			System.out.println("Error loading tracks: " + e.getMessage());
		}
	}



	private void reverseSortingOrder() {
		asc = !asc;
		db.sort(theComp, asc);
		System.out.println("Sorting order reversed.");
	}

	private void selectSorting() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select sorting option:");
		System.out.println("1: By title");
		System.out.println("2: By duration");
		System.out.println("3: By writer");
		System.out.println("4: By performer");
		System.out.println("5: By year");

		int sortingOption = Integer.parseInt(scanner.nextLine());

		switch (sortingOption) {
			case 1:
				theComp = new TitleComparator();
				break;
			case 2:
				theComp = new DurationComparator();
				break;
			case 3:
				theComp = new WriterComparator();
				break;
			case 4:
				theComp = new PerformerComparator();
				break;
			case 5:
				theComp = new YearComparator();
				break;
			default:
				System.out.println("Invalid sorting option.");
				return;
		}

		db.sort(theComp, asc);
		System.out.println("Sorting applied.");
	}

	private void export_to_xml(){
		if(db.size() == 0){
			System.out.print("no records stored.\n");
			return;
		}
		if(db.selection().length == 0){
			System.out.print("selection empty.\n");
			return;
		}

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the file path (without file name): ");
		String directoryPath = scanner.nextLine();

		System.out.print("Enter the name of the file (without extension): ");
		String fileName = scanner.nextLine();

		String filePath = directoryPath + "/" + fileName + ".xml";

		// Generate XML content from database
		StringBuilder xmlContent = new StringBuilder();
		xmlContent.append("<tracks>\n");

		List<Track> tracks = db.getAllTracks(); // Assume this method returns all tracks in the database
		for (Track track : tracks) {
			xmlContent.append("    <track>\n");
			xmlContent.append("        <title>").append(track.getTitle()).append("</title>\n");
			xmlContent.append("        <writer>").append(track.getWriter().getName()).append("</writer>\n");
			xmlContent.append("        <performer>").append(track.getPerformer().getName()).append("</performer>\n");
			xmlContent.append("        <duration>").append(formatDuration(track.getDuration())).append("</duration>\n");
			xmlContent.append("        <year>").append(track.getYear()).append("</year>\n");
			xmlContent.append("    </track>\n");
		}

		xmlContent.append("</tracks>");

		// Write the XML content to the file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(xmlContent.toString());
			System.out.println("XML content has been written to " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		scanner.close();

	}

	private void selectFormatting() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select formatting option:");
		System.out.println("1: Long format [Title Writer (min:sec)]");
		System.out.println("2: Short format [Title      (min:sec)]");
		System.out.println("3: CSV format [Title, Writer, Performer, duration, year]");

		int formattingOption = Integer.parseInt(scanner.nextLine());

		switch (formattingOption) {
			case 1:
				theFormat = new LongTrackFormatter();
				break;
			case 2:
				theFormat = new ShortTrackFormatter();
				break;
			case 3:
				theFormat = new CSVTrackFormatter();
				break;
			default:
				System.out.println("Invalid formatting option.");
				return;
		}

		System.out.println("Formatting applied.");
	}

	private String formatDuration(int duration) {
		int minutes = duration / 60;
		int seconds = duration % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}


}
