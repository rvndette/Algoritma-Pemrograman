import java.util.Arrays;
import java.util.Scanner;

class DjikstraAlgorithm {
    private final int[][] adjMatrix; // Matriks adjacency untuk menyimpan bobot antar simpul
    private final String[] vertexData; // Array untuk menyimpan data simpul (nama simpul)
    private final int size; // Jumlah simpul dalam graf

    // Konstruktor untuk inisialisasi graf
    public DjikstraAlgorithm(int size) {
        this.size = size; // Jumlah simpul dalam graf
        this.adjMatrix = new int[size][size]; // Matriks adjacency diinisialisasi
        this.vertexData = new String[size]; // Array untuk menyimpan nama simpul
        Arrays.fill(this.vertexData, ""); // Isi awal vertexData adalah string kosong
    }

    // Fungsi untuk menambahkan edge ke dalam graf
    public void addEdge(int u, int v, int weight) {
        // Periksa apakah simpul u dan v valid (dalam rentang)
        if (u >= 0 && u < size && v >= 0 && v < size) {
            adjMatrix[u][v] = weight; // Tambahkan edge dari u ke v (graf berarah)
            adjMatrix[v][u] = weight; // Tambahkan edge dari v ke u (graf tak berarah)
        }
    }

    // Fungsi untuk menambahkan data ke simpul tertentu
    public void addVertexData(int vertex, String data) {
        if (vertex >= 0 && vertex < size) { // Periksa apakah indeks simpul valid
            vertexData[vertex] = data; // Simpan data pada simpul yang sesuai
        }
    }

    // Algoritma Dijkstra untuk menghitung jarak terpendek dari simpul awal
    public int[] dijkstra(String startVertexData) {
        // Temukan indeks simpul awal berdasarkan data simpul
        int startVertex = Arrays.asList(vertexData).indexOf(startVertexData);
        if (startVertex == -1) { // Jika simpul tidak ditemukan, lempar exception
            throw new IllegalArgumentException("Vertex tidak ditemukan.");
        }

        // Inisialisasi array jarak dan array visited
        int[] distances = new int[size];
        boolean[] visited = new boolean[size];
        Arrays.fill(distances, Integer.MAX_VALUE); // Jarak awal adalah tak hingga
        distances[startVertex] = 0; // Jarak dari simpul awal ke dirinya sendiri adalah 0

        // Proses utama algoritma Dijkstra
        for (int i = 0; i < size; i++) {
            int u = getMinDistanceVertex(distances, visited); // Temukan simpul dengan jarak minimum
            if (u == -1) break; // Jika tidak ada simpul yang tersisa, keluar dari loop

            visited[u] = true; // Tandai simpul ini sebagai telah dikunjungi

            // Perbarui jarak ke tetangga simpul yang belum dikunjungi
            for (int v = 0; v < size; v++) {
                if (adjMatrix[u][v] != 0 && !visited[v] && distances[u] != Integer.MAX_VALUE) {
                    int alt = distances[u] + adjMatrix[u][v]; // Hitung jarak alternatif
                    if (alt < distances[v]) {
                        distances[v] = alt; // Perbarui jarak jika lebih pendek
                    }
                }
            }
        }
        return distances; // Kembalikan array jarak
    }

    // Fungsi untuk menemukan simpul dengan jarak minimum yang belum dikunjungi
    private int getMinDistanceVertex(int[] distances, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minIndex = -1;

        // Cari simpul dengan jarak minimum
        for (int i = 0; i < size; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                minIndex = i;
            }
        }
        return minIndex; // Kembalikan indeks simpul dengan jarak minimum
    }

    // Fungsi untuk mendapatkan data simpul berdasarkan indeks
    public String getVertexData(int index) {
        return vertexData[index];
    }

    // Fungsi utama untuk menjalankan program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Memasukkan jumlah simpul
            System.out.print("Masukkan jumlah simpul: ");
            int size = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline
            DjikstraAlgorithm graph = new DjikstraAlgorithm(size);

            // Memasukkan data untuk setiap simpul
            System.out.println("Masukkan data simpul:");
            for (int i = 0; i < size; i++) {
                System.out.print("Simpul " + i + ": ");
                String data = scanner.next();
                graph.addVertexData(i, data);
            }

            // Memasukkan jumlah edge
            System.out.println("Masukkan jumlah edge: ");
            int edges = scanner.nextInt();

            // Memasukkan detail untuk setiap edge
            System.out.println("Masukkan setiap edge (u v weight):");
            for (int i = 0; i < edges; i++) {
                int u = scanner.nextInt(); // Simpul asal
                int v = scanner.nextInt(); // Simpul tujuan
                int weight = scanner.nextInt(); // Bobot
                graph.addEdge(u, v, weight);
            }

            // Memasukkan simpul awal untuk Dijkstra
            System.out.print("Masukkan simpul awal: ");
            String startVertex = scanner.next();

            // Menjalankan algoritma Dijkstra dan mencetak hasil
            System.out.println("\nHasil Algoritma Dijkstra:");
            try {
                int[] distances = graph.dijkstra(startVertex);
                for (int i = 0; i < size; i++) {
                    System.out.println("Jarak terpendek dari " + startVertex + " ke " + graph.getVertexData(i) + ": " + (distances[i] == Integer.MAX_VALUE ? "INF" : distances[i]));
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            // Menanyakan apakah pengguna ingin mengulang
            System.out.print("\nApakah Anda ingin menjalankan lagi? (y/n): ");
            String repeat = scanner.next();
            if (!repeat.equalsIgnoreCase("y")) {
                break; // Keluar dari loop jika pengguna memilih tidak
            }
        }

        scanner.close(); // Menutup scanner untuk menghindari kebocoran sumber daya
    }
}
