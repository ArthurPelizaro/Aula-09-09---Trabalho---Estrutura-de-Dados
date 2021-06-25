import java.util.Stack;
import java.util.Scanner;

public class HanoiIterativo {

	private static Stack<String> pilha = new Stack<String>();

	private static long numMov;

	private static void mover(int O, int D) {
		numMov++;
		System.out.println("[" + numMov + "]:" + O + " -> " + D);
	}

	public static void hanoi(int n) {

		int O = 1; 
		int D = 3; 
		int T = 2; 

		String comandoAtual = n + "," + O + "," + D + "," + T;

		pilha.push(comandoAtual);

		while (!pilha.empty()) {

			if (n > 1) {

				n--;
				String[] vetAux = comandoAtual.split(",");
				O = Integer.parseInt(vetAux[1]);
				D = Integer.parseInt(vetAux[2]);
				T = Integer.parseInt(vetAux[3]);

				comandoAtual = n + "," + O + "," + T + "," + D;

				pilha.push(comandoAtual);

			} else {

				comandoAtual = pilha.pop();

				String[] vetAux = comandoAtual.split(",");
				n = Integer.parseInt(vetAux[0]);
				O = Integer.parseInt(vetAux[1]);
				D = Integer.parseInt(vetAux[2]);
				T = Integer.parseInt(vetAux[3]);

				mover(O, D);

				if (n > 1) {
					n--;
					comandoAtual=n + "," + T + "," + D + "," + O;
					pilha.push(comandoAtual);
				}

			}

		}

	}

	public static void main(String[] args) {

		int n; 

		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite o número de discos: ");
		n = entrada.nextInt();

		HanoiIterativo.hanoi(n);

	}

}