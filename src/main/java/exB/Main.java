package exB;
/*
Autor: Nicolas Crivelli

	Clase principal de ejecución para el ejercicio B
*/ 

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
	
		Reader lector = new Reader();
		ArrayList<String> nodes = lector.readDat("./src/main/resources/stars.dat");
		
		Graph graph = new Graph();
		graph.createGraph(nodes);
		
	}

}
