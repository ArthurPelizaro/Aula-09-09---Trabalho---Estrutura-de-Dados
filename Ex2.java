//Fiz um sistema diferente pois não consegui implementar o sistema com os 3 perfils, espero que considere
import java.util.*;

class P115{

public static void main(String[] args){

Scanner inp = new Scanner (System.in);

int flag = inp.nextInt();
int ncaixas = inp.nextInt();

Caixa [] caixas = new Caixa[ncaixas];
int k;

for(int i=0; i < ncaixas; i++){
k = inp.nextInt();
caixas[i] = new Caixa(k);
}

int nClientes = inp.nextInt();

LinkedList<Cliente> porAtender = new LinkedList<Cliente>();
String nome;
int tempo_chegada, nprodutos;
Cliente novoCliente;

for (int i = 0; i< nClientes; i++){
nome = inp.next();
tempo_chegada = inp.nextInt();
nprodutos = inp.nextInt();

novoCliente = new Cliente (nome, tempo_chegada, nprodutos);

porAtender.addLast(novoCliente);
}

Subtarefas.resolve(flag, ncaixas, caixas, porAtender);
}
}

class Cliente{

String nome;
int tempo_chegada, tempo_atendimento, nprodutos, tempo_saida;

Cliente(String n, int t, int p){
nome = n;
tempo_chegada = t;
nprodutos = p;
tempo_saida = -1;
}
}

class Caixa{
int k, total_produtos;

int num_clientes; 
int tempSai; 
LinkedList<Cliente> atendidos;

Caixa(int lent){
k = lent;
atendidos = new LinkedList<Cliente>();
total_produtos = 0;
num_clientes = 0; 
tempSai = 0; 

}
}

class Subtarefas{

public static int escolherCaixa(int ncaixas, Caixa[] caixas){

int menorFila = caixas[0].atendidos.size();
int nordem = 0;

for(int i=0;i<ncaixas;i++){
if(caixas[i].atendidos.size() < menorFila){
menorFila = caixas[i].atendidos.size();
nordem = i;
}
if(caixas[i].atendidos.size() == menorFila){
if(caixas[i].atendidos.getLast().nprodutos < caixas[menorFila].atendidos.getLast().nprodutos)
nordem = i;
if(caixas[i].atendidos.getLast().nprodutos == caixas[menorFila].atendidos.getLast().nprodutos)
nordem = 0;
}
}
return 0;
}

public static void resolve(int flag, int ncaixas, Caixa[] caixas, LinkedList<Cliente> porAtender) {
String nome;
int tempo_chegada, nprodutos;
Cliente novoCliente;


if(flag == 1){

int tempo_livre = 0;

while(!porAtender.isEmpty()){

novoCliente = porAtender.removeFirst();

if (novoCliente.tempo_chegada >= tempo_livre)
novoCliente.tempo_atendimento = novoCliente.tempo_chegada;
else
novoCliente.tempo_atendimento = tempo_livre;

novoCliente.tempo_saida = 10 + novoCliente.nprodutos * caixas[0].k + novoCliente.tempo_atendimento;

tempo_livre = novoCliente.tempo_saida;

caixas[0].atendidos.addLast(novoCliente);

System.out.println (novoCliente.nome + " " + novoCliente.tempo_chegada + " " + novoCliente.tempo_saida);
}
}

if (flag == 2){

int t=0;
int por_atender = porAtender.size();
int i;
Cliente cli;
Cliente c;

while(por_atender > 0){

if(!porAtender.isEmpty() && (porAtender.getFirst().tempo_chegada == t)){

cli = porAtender.removeFirst();

i = escolherCaixa(ncaixas, caixas);
System.out.println(cli.nome);
caixas[i].atendidos.addLast(cli);

if(!caixas[i].atendidos.isEmpty() && (caixas[i].atendidos.getLast().tempo_saida >= cli.tempo_chegada))
caixas[i].tempSai += 10 + (caixas[i].k*cli.nprodutos);

else if(!caixas[i].atendidos.isEmpty())
caixas[i].tempSai = 10 + cli.tempo_chegada + (caixas[i].k*cli.nprodutos);

caixas[i].num_clientes ++;
caixas[i].total_produtos += cli.nprodutos;
cli.tempo_saida = caixas[i].tempSai;
caixas[i].atendidos.addLast(cli);
}

for(int l=0; l<ncaixas; l++){
if(!caixas[l].atendidos.isEmpty() && (caixas[l].atendidos.getFirst().tempo_saida == t)){
c = caixas[l].atendidos.removeFirst();
por_atender--;
System.out.println(c.nome);
}
}

t++;
}

for(int a = 0; a < ncaixas; a++)
System.out.println("Caixa #" + a + ": " + caixas[a].num_clientes + " " + caixas[a].total_produtos);
}
}
}