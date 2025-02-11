import java.io.*;

public interface Instruction {
	public void execute(TISC tisc);
	public String toString();
}

	/*********************************************************************|
	|                      Instruções aritméticas                         |
	|*********************************************************************/


class Add implements Instruction {
	public Add() {
		super();
	}

	public void execute(TISC tisc) {
		if(tisc.eval_stack.size() < 2)
			System.err.println("Not enough arguments to ADD\n");

		else{
			Integer b = tisc.eval_stack.pop();
			Integer a = tisc.eval_stack.pop();

			tisc.eval_stack.push(a + b);
			tisc.PC++;
		}
	}

	@Override
	public String toString() {
		return "add";
	}
}

class Sub implements Instruction {
	public Sub() {
		super();
	}

	public void execute(TISC tisc) {
		if(tisc.eval_stack.size() < 2)
			System.err.println("Not enough arguments to SUB\n");

		else{
			Integer b = tisc.eval_stack.pop();
			Integer a = tisc.eval_stack.pop();

			tisc.eval_stack.push(a - b);
			tisc.PC++;
		}
	}

	@Override
	public String toString() {
		return "sub";
	}
}

class Mult implements Instruction {
	public Mult() {
		super();
	}

	public void execute(TISC tisc) {
		if(tisc.eval_stack.size() < 2)
			System.err.println("Not enough arguments to MULT\n");

		else{
			Integer b = tisc.eval_stack.pop();
			Integer a = tisc.eval_stack.pop();

			tisc.eval_stack.push(a * b);
			tisc.PC++;
		}
	}

	@Override
	public String toString() {
		return "mult";
	}
}

class Div implements Instruction {
	public Div() {
		super();
	}

	public void execute(TISC tisc) {
		if(tisc.eval_stack.size() < 2)
			System.err.println("Not enough arguments to DIV\n");

		else{
			Integer b = tisc.eval_stack.pop();
			Integer a = tisc.eval_stack.pop();

			tisc.eval_stack.push(a / b);
			tisc.PC++;
		}
	}

	@Override
	public String toString() {
		return "div";
	}
}

class Mod implements Instruction {
	public Mod() {
		super();
	}

	public void execute(TISC tisc) {
		if(tisc.eval_stack.size() < 2)
			System.err.println("Not enough arguments to MOD\n");

		else{
			Integer b = tisc.eval_stack.pop();
			Integer a = tisc.eval_stack.pop();

			tisc.eval_stack.push(a % b);
			tisc.PC++;
		}
	}

	@Override
	public String toString() {
		return "mod";
	}
}

class Exp implements Instruction {
	public Exp() {
		super();
	}

	public void execute(TISC tisc) {
		if(tisc.eval_stack.size() < 2)
			System.err.println("Not enough arguments to EXP\n");

		else{
			Integer b = tisc.eval_stack.pop();
			Integer a = tisc.eval_stack.pop();

			tisc.eval_stack.push(a ^ b);
			tisc.PC++;
		}
	}

	@Override
	public String toString() {
		return "exp";
	}
}


	/*********************************************************************|
	|              Instruções para manipulação de inteiros                |
	|*********************************************************************/


class Push_int implements Instruction {
	int integer;

	public Push_int(int integer) {
		super();
		this.integer = integer;
	}

	public void execute(TISC tisc) {
		tisc.eval_stack.push(integer);
		tisc.PC++;
	}

	@Override
	public String toString() {
		return "push_int " + this.integer;
	}
}


	/*********************************************************************|
	|                   Instruções de acesso a variáveis                  |
	|*********************************************************************/


class Push_var implements Instruction {
	int integer1, integer2;

	public Push_var(int integer1, int integer2) {
		super();
		this.integer1 = integer1;
		this.integer2 = integer2;
	}

	public void execute(TISC tisc) {
		int temp = tisc.EP;

		// Faz chegar a temp ao RA certo seguindo o AL
		for(int i = 0; i < integer1; i++)
			temp = tisc.exe_memo.get(temp + 1);

		
		// Contas baseadas nos diagramas:
		// n_args: Salta CL, AL, ER, n_args
		// value: Salta CL, AL, ER, n_args, n_vars, args
		int n_args = tisc.exe_memo.get(temp + 3);
		int value = tisc.exe_memo.get(temp + 4 + n_args + integer2);

		tisc.eval_stack.push(value);
		tisc.PC++;
	}

	@Override
	public String toString() {
		return "push_var " + integer1 + " " + integer2;
	}
}

class Store_var implements Instruction {
	int integer1, integer2;

	public Store_var(int integer1, int integer2) {
		super();
		this.integer1 = integer1;
		this.integer2 = integer2;
	}

	public void execute(TISC tisc) {
		int temp = tisc.EP;

		int value = tisc.eval_stack.pop();

		// Faz chegar a temp ao RA certo seguindo o AL
		for(int i = 0; i < integer1; i++)
			temp = tisc.exe_memo.get(temp + 1);


		// Contas baseadas nos diagramas:
		// n_args: Salta CL, AL, ER, n_args
		// Sitio certo: Salta CL, AL, ER, n_args, n_vars, args
		int n_args = tisc.exe_memo.get(temp + 3);
		tisc.exe_memo.set(temp + 4 + n_args + integer2, value);
		tisc.PC++;
	}

	@Override
	public String toString() {
		return "store_var " + integer1 + " " + integer2;
	}
}


	/*********************************************************************|
	|                    Instruções de acesso a argumentos                |
	|*********************************************************************/


class Push_arg implements Instruction {
	int integer1, integer2;

	public Push_arg(int integer1, int integer2) {
		super();
		this.integer1 = integer1;
		this.integer2 = integer2;
	}

	public void execute(TISC tisc) {
		int temp = tisc.EP;

		// Faz chegar a temp ao RA certo seguindo o AL
		for(int i = 0; i < integer1; i++) {
			temp = tisc.exe_memo.get(temp + 1);
		}

		// Contas baseadas nos diagramas:
		// value: Salta CL, AL, ER, n_args, n_vars
		int value = tisc.exe_memo.get(temp + 4 + integer2);

		tisc.eval_stack.push(value);
		tisc.PC++;
	}

	@Override
	public String toString() {
		return "push_arg " + integer1 + " " + integer2;
	}
}

class Store_arg implements Instruction {
	int integer1, integer2;

	public Store_arg(int integer1, int integer2) {
		super();
		this.integer1 = integer1;
		this.integer2 = integer2;
	}

	public void execute(TISC tisc) {
		int temp = tisc.EP;
		int value = tisc.eval_stack.pop();

		// Faz chegar a temp ao RA certo seguindo o AL
		for(int i = 0; i < integer1; i++) {
			temp = tisc.exe_memo.get(temp + 1);
		}

		// Contas baseadas nos diagramas:
		// Sitio certo: Salta CL, AL, ER, n_args, n_vars
		tisc.exe_memo.set(temp + 4 + integer2, value);
		tisc.PC++;
	}

	@Override
	public String toString() {
		return "store_arg " + integer1 + " " + integer2;
	}
}


	/*********************************************************************|
	|                   Instruções para chamada de funções                |
	|*********************************************************************/


class Set_arg implements Instruction {
	int integer1;

	public Set_arg(int integer1) {
		super();
		this.integer1 = integer1;
	}

	public void execute(TISC tisc) {
		int value = tisc.eval_stack.pop();

		// Guarda o novo argumento entre o RA anterior e o proximo
		tisc.exe_memo.add(value);
		tisc.PC++;
	}

	@Override
	public String toString() {
		return "set_arg " + integer1;
	}
}

class Call implements Instruction {
	int integer1;
	String label;

	public Call(int integer1, String label) {
		super();
		this.integer1 = integer1;
		this.label = label;
	}

	public void execute(TISC tisc) {
		// Cria um novo RA
		tisc.exe_memo.add(tisc.EP); // Cria um CL
		
		// Atualiza o EP
		int old_EP = tisc.EP;
		tisc.EP = tisc.exe_memo.size() - 1;

		// Cria um AL
		int new_AL;
		/* Se integer1 for:
		-1 -> AL = CL do bloco atual
		 0 -> AL = AL do bloco atual
		>0 -> chamada recursiva do AL até chegar ao scope que fica a zero
		*/
		if(integer1 < 0)
			new_AL = tisc.exe_memo.get(tisc.EP);

		else{
		    new_AL = tisc.exe_memo.get(old_EP + 1);
			for(int i = 1; i < integer1; i++)
				new_AL = tisc.exe_memo.get(new_AL + 1);
		}
		tisc.exe_memo.add(new_AL);

		// Cria um ER
		tisc.exe_memo.add(tisc.PC + 1);
		// Salta para a nova label
		tisc.PC = tisc.label_manager.get(label);
	}

	@Override
	public String toString() {
		return "call " + integer1 + " " + label;
	}
}

class Locals implements Instruction {
	int integer1, integer2;

	public Locals(int integer1, int integer2) {
		super();
		this.integer1 = integer1;
		this.integer2 = integer2;
	}

	public void execute(TISC tisc) {

		// Adiciona n_args e n_vars
		tisc.exe_memo.add(integer1);
		tisc.exe_memo.add(integer2);

		// Vai buscar os argumentos
		int start = tisc.exe_memo.get(tisc.EP);
        
		if(start != -1) {

			int n_args_ant = tisc.exe_memo.get(start + 3);
			int n_vars_ant = tisc.exe_memo.get(start + 4);


			start += 5 + n_args_ant + n_vars_ant;

			for (int i = 0; i < integer1; i++) {
				tisc.exe_memo.add(tisc.exe_memo.remove(start));
				tisc.EP--;
			}
		}

		// Inicializa as novas variaveis e argumentos
		for (int k = 0; k < integer2; k++)
			tisc.exe_memo.add(-1);

		tisc.PC++;
	}

	@Override
	public String toString() {
		return "locals " + integer1 + " " + integer2;
	}
}

class Return implements Instruction {
	
	public Return() {
		super();
	}

	public void execute(TISC tisc) {
		int old_EP = tisc.EP;
		tisc.PC = tisc.exe_memo.get(tisc.EP + 2);	// Endereço de retorno
		tisc.EP = tisc.exe_memo.get(tisc.EP);		// Atualiza o EP

		// Remove o RA que foi retornado
		while(old_EP != tisc.exe_memo.size())
			tisc.exe_memo.remove(old_EP);
	}

	@Override
	public String toString() {
		return "return";
	}
}


	/*********************************************************************|
	|                        Instruções de salto                          |
	|*********************************************************************/


class Jump implements Instruction {
	String label;

	public Jump(String label) {
		super();
		this.label = label;
	}

	public void execute(TISC tisc) {
		tisc.PC = tisc.label_manager.get(label); // Muda o valor do PC
	}

	@Override
	public String toString() {
		return "jump " + label;
	}
}

class Jeq implements Instruction {
	String label;

	public Jeq(String label) {
		super();
		this.label = label;
	}

	public void execute(TISC tisc) {
		int b = tisc.eval_stack.pop();	// Verifica se a == b
		int a = tisc.eval_stack.pop();

		if(a == b)
			tisc.PC = tisc.label_manager.get(label); // Muda o valor do PC
		else
			tisc.PC++;
	}

	@Override
	public String toString() {
		return "jeq " + label;
	}
}

class Jlt implements Instruction {
	String label;

	public Jlt(String label) {
		super();
		this.label = label;
	}

	public void execute(TISC tisc) {
		int b = tisc.eval_stack.pop();		// Verifica se a < b
		int a = tisc.eval_stack.pop();

		if(a < b)
			tisc.PC = tisc.label_manager.get(label); // Muda o valor do PC
		else
			tisc.PC++;
	}

	@Override
	public String toString() {
		return "jlt " + label;
	}
}


	/*********************************************************************|
	|                          Instruções de saı́da                        |
	|*********************************************************************/


class Print implements Instruction {
	public Print() {
		super();
	}

	public void execute(TISC tisc) {
		int a = tisc.eval_stack.pop();	// Tira da stack

		System.out.print(a);			// Imprime o valor
		tisc.PC++;
	}

	@Override
	public String toString() {
		return "print";
	}
}

class Print_str implements Instruction {
	String string;

	public Print_str(String string) {
		super();
		this.string = string;
	}

	public void execute(TISC tisc) {
		System.out.print(string);		// Imprime a string
		tisc.PC++;
	}

	@Override
	public String toString() {
		return "print_str " + string;
	}
}

class Print_nl implements Instruction {
	public Print_nl() {
		super();
	}

	public void execute(TISC tisc) {
		System.out.println();			// Imprime uma nova linha 
		tisc.PC++;
	}

	@Override
	public String toString() {
		return "print_nl";
	}
}

