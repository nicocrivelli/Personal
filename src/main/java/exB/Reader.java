package exB;
/*
 Autor: Nicolas Crivelli
 Metodos:
 	public ArrayList<String> readDat(String url)
 		Recibe la url donde se encuentra el archivo .dat con la información de las estrellas
 		y guarda en un ArrayList únicamente los nombres de las estrellas, el cual luego es retornado.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Reader {

	public ArrayList<String> readDat(String url) {

		ArrayList<String> stars = new ArrayList<>();

		try {
			BufferedReader buf = new BufferedReader(new FileReader(url));
			String line = null;
			String[] wordsArray;

			while (true) {
				line = buf.readLine();
				if (line == null) {
					break;
				} else {
					wordsArray = line.split("\t");
					int columns = 0;
					for (String each : wordsArray) {
						if (!"".equals(each)) {
						//	stars.add(each);

							if (!each.equals("CONSTELLATION") && !each.equals("|HIP0") && !each.equals("|HIP1")
									&& !each.equals("|STAR0") && !each.equals("|STAR1")) {
								switch (columns) {
								case 0:
									columns++;
									break;
								case 1:
									columns++;
									break;
								case 2:
									columns++;
									break;
								case 3:
									stars.add(each.replace("|", ""));
									columns++;
									break;
								case 4:
									stars.add(each.replace("|", ""));
									columns++;
									break;
								}
							}
						}
					}
				}
			}

			buf.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return stars;
	}
}
