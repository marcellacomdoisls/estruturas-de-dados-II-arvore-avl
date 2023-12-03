import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MergeSort {
  // Função para ordenação usando o Merge Sort
  static void mergeSort(int[] arr, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;

      // Divide e conquista
      mergeSort(arr, left, mid);
      mergeSort(arr, mid + 1, right);

      // Junta as partes ordenadas
      merge(arr, left, mid, right);
    }
  }

  static void merge(int[] arr, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;

    int[] L = new int[n1];
    int[] R = new int[n2];

    // Copia os dados para os arrays temporários L[] e R[]
    System.arraycopy(arr, left, L, 0, n1);
    System.arraycopy(arr, mid + 1, R, 0, n2);

    // Junta os arrays temporários de volta ao array original
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
      if (L[i] <= R[j]) {
        arr[k] = L[i];
        i++;
      } else {
        arr[k] = R[j];
        j++;
      }
      k++;
    }

    // Copia os elementos restantes de L[], se houver algum
    while (i < n1) {
      arr[k] = L[i];
      i++;
      k++;
    }

    // Copia os elementos restantes de R[], se houver algum
    while (j < n2) {
      arr[k] = R[j];
      j++;
      k++;
    }
  }

  public static void main(String[] args) {
    try {
      // Leitura do arquivo.txt
      BufferedReader br = new BufferedReader(
          new FileReader("E:\\Faculdade\\4º Período\\Estruturas de Dados II\\dados100_mil.txt"));
      String line;
      int[] dataForSorting = new int[1000];
      int index = 0;

      while ((line = br.readLine()) != null) {
        line = line.trim(); // Remove espaços extras no início e no final da linha
        if (!line.isEmpty()) { // Verifica se a linha não está vazia
          try {
            int data = Integer.parseInt(line);
            dataForSorting[index] = data;
            index++;
          } catch (NumberFormatException e) {
            System.out.println("Erro ao converter para inteiro: " + line);
          }
        }
      }

      br.close();

      // Medição do tempo para o Merge Sort
      long startTimeMergeSort = System.currentTimeMillis();
      mergeSort(dataForSorting, 0, index - 1);
      long endTimeMergeSort = System.currentTimeMillis();

      // Imprime os dados ordenados pelo Merge Sort
      System.out.print("Dados ordenados pelo Merge Sort: ");
      for (int i = 0; i < index; i++) {
        System.out.print(dataForSorting[i] + " ");
      }
      System.out.println("\nNúmero de elementos ordenados pelo Merge Sort: " + index);
      System.out.println("Tempo de execução do Merge Sort: " + (endTimeMergeSort - startTimeMergeSort) + "ms");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
