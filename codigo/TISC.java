import java.util.*;

// Tiny Instruction Set Computer

public class TISC {
	int EP, PC, CL;
	Vector<Instruction> inst_memo;
	HashMap<String, Integer> label_manager;
	HashMap<Integer, String> label_manager_aux;
	Stack<Integer> eval_stack;
	Vector<Integer> exe_memo;

	public TISC() {
		this.EP = 0;
		this.PC = 0;
		this.inst_memo = new Vector<Instruction>();
		this.label_manager = new HashMap<String, Integer>();
		this.label_manager_aux = new HashMap<Integer, String>();
		this.eval_stack = new Stack<Integer>();
		this.exe_memo = new Vector<Integer>();
	}

	public void label_new(String label) {
		label_manager.put(label, inst_memo.size());
		label_manager_aux.put(inst_memo.size(), label);
	}

	public void aritmetic_inst_new(String kind) {

		switch(kind) {
			case "ADD":
				inst_memo.add(new Add());
				break;
			case "SUB":
				inst_memo.add(new Sub());
				break;
			case "MULT":
				inst_memo.add(new Mult());
				break;
			case "DIV":
				inst_memo.add(new Div());
				break;
			case "MOD":
				inst_memo.add(new Mod());
				break;
			case "EXP":
				inst_memo.add(new Exp());
				break;
			default:
				break;
		}
	}

	public void man_int_inst_new(String integer) {
		this.inst_memo.add(new Push_int(Integer.parseInt(integer)));
	}

	public void access_vars_inst_new(String kind, String integer1, String integer2) {
		
		int int1 = Integer.parseInt(integer1);
		int int2 = Integer.parseInt(integer2);

		switch(kind) {
			case "PUSH":
				inst_memo.add(new Push_var(int1, int2));
				break;
			case "STORE":
				inst_memo.add(new Store_var(int1, int2));
				break;
			default:
				break;
		}
	}

	public void access_args_inst_new(String kind, String integer1, String integer2) {

		int int1 = Integer.parseInt(integer1);
		int int2 = Integer.parseInt(integer2);

		switch(kind) {
			case "PUSH":
				inst_memo.add(new Push_args(int1, int2));
				break;
			case "STORE":
				inst_memo.add(new Store_args(int1, int2));
				break;
			default:
				break;
		}
	}

	public void funct_call_inst_new(String kind, String integer1, String integer2, String label) {

		int int1, int2;
		
		switch(kind) {
			case "SET_ARG":
				int1 = Integer.parseInt(integer1);
				inst_memo.add(new Set_arg(int1));
				break;
			case "CALL":
				int1 = Integer.parseInt(integer1);
				inst_memo.add(new Call(int1, label));
				break;
			case "LOCALS":
				int1 = Integer.parseInt(integer1);
				int2 = Integer.parseInt(integer2);
				inst_memo.add(new Locals(int1, int2));
				break;
			case "RETURN":
				inst_memo.add(new Return());
				break;
			default:
				break;
		}
	}

	public void jump_inst_new(String kind, String label) {
		
		switch(kind) {
			case "JUMP":
				inst_memo.add(new Jump(label));
				break;
			case "JEQ":
				inst_memo.add(new Jeq(label));
				break;
			case "JLT":
				inst_memo.add(new Jlt(label));
				break;
			default:
				break;
		}
	}

	public void exit_inst_new(String kind, String string) {
		
		switch(kind) {
			case "PRINT":
				inst_memo.add(new Print());
				break;
			case "PRINT_STR":
				inst_memo.add(new Print_str(string));
				break;
			case "PRINT_NL":
				inst_memo.add(new Print_nl());
				break;
			default:
				break;
		}
	}

	/** Executa o programa TISC carregado na maquina. */
	public void executa()
	{
		for(int i = 0; i < inst_memo.size(); i++) {
			this.PC++;
			System.out.println(inst_memo.get(i).toString());
		}
	}
}
