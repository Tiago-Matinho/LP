import java.util.*;

// Tiny Instruction Set Computer

public class TISC {
  int EP, PC;
  Vector<Instruction> inst_memo;
  HashMap<String, Integer> label_manager;
  Stack<String> eval_stack;

  public TISC() {
    this.EP = 0;
    this.PC = 0;
    this.inst_memo = new Vector<Instruction>();
    this.label_manager = new HashMap<String, Integer>();
    this.eval_stack = new Stack<String>();
  }

  public void label_new(String label) {
    label_manager.put(label, inst_memo.size());
  }

  public void aritmetic_inst_new(String kind) {
    this.inst_memo.add(new Aritmectic_inst(kind));
  }

  public void man_int_inst_new(String integer) {
    this.inst_memo.add(new Manipulate_int_inst(integer));
  }


  public void access_vars_inst_new(String kind, String integer1, String integer2) {
    this.inst_memo.add(new Access_vars_inst(kind, integer1, integer2));
  }


  public void access_args_inst_new(String kind, String integer1, String integer2) {
    this.inst_memo.add(new Access_args_inst(kind, integer1, integer2));
  }


  public void funct_call_inst_new(String kind, String integer1, String integer2, String label) {
    this.inst_memo.add(new Funct_call_inst(kind, integer1, integer2, label));
  }


  public void jump_inst_new(String kind, String label) {
    this.inst_memo.add(new Jump_inst(kind, label));
  }


  public void exit_inst_new(String kind, String label) {
    this.inst_memo.add(new Exit_inst(kind, label));
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
