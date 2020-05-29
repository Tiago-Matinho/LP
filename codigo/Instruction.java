import java.util.*;

public interface Instruction {
    public void execute(TISC tisc);
    public String toString();
}

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
        }
    }

    @Override
    public String toString() {
        return "exp";
    }
}

class Push_int implements Instruction {
    int integer;

    public Push_int(int integer) {
        super();
        this.integer = integer;
    }

    public void execute(TISC tisc) {
        tisc.eval_stack.push(integer);
    }

    @Override
    public String toString() {
        return "push_int " + this.integer;
    }
}

// FIXME
class Push_var implements Instruction {
    int integer1, integer2;

    public Push_var(int integer1, int integer2) {
        super();
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    public void execute(TISC tisc) {
        int temp = tisc.EP;

        // faz chegar a temp ao Ra certo
        for(int i = 0; i < integer1; i++) {
            temp = tisc.exe_memo.get(temp);
        }
        
        // contas baseadas nos diagramas
        int n_args = tisc.exe_memo.get(temp + 2); // salta AL, ER
        int value = tisc.exe_memo.get(temp + 3 + n_args + integer2);

        tisc.eval_stack.push(value);
    }

    @Override
    public String toString() {
        return "push_var";
    }
}

// FIXME
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

        // faz chegar a temp o contexto certo
        for(int i = 0; i < integer1; i++) {
            temp = tisc.exe_memo.get(temp);
        }

        // contas baseadas nos diagramas
        int n_args = tisc.exe_memo.get(temp + 2); // salta AL, ER
        tisc.exe_memo.set(temp + 3 + n_args + integer2, value);
    }

    @Override
    public String toString() {
        return "store_var";
    }
}

// FIXME
class Push_args implements Instruction {
    int integer1, integer2;

    public Push_args(int integer1, int integer2) {
        super();
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    public void execute(TISC tisc) {
        int temp = tisc.EP;

        // faz chegar a temp o contexto certo
        for(int i = 0; i < integer1; i++) {
            temp = tisc.exe_memo.get(temp);
        }

        int value = tisc.exe_memo.get(temp + 3 + integer2);

        tisc.eval_stack.push(value);
    }

    @Override
    public String toString() {
        return "push_args";
    }
}

// FIXME
class Store_args implements Instruction {
    int integer1, integer2;

    public Store_args(int integer1, int integer2) {
        super();
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    public void execute(TISC tisc) {
        int temp = tisc.EP;
        int value = tisc.eval_stack.pop();

        // faz chegar a temp o contexto certo
        for(int i = 0; i < integer1; i++) {
            temp = tisc.exe_memo.get(temp);
        }


        tisc.exe_memo.set(temp + 3 + integer2, value);
    }

    @Override
    public String toString() {
        return "store_args";
    }
}

// FIXME
class Set_arg implements Instruction {
    int integer1;

    public Set_arg(int integer1) {
        super();
        this.integer1 = integer1;
    }

    public void execute(TISC tisc) {
        int value = tisc.eval_stack.pop();

        tisc.exe_memo.set(tisc.EP + 4 + integer1, value);
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
        // Cria um CL
        tisc.exe_memo.add(tisc.EP);
        tisc.EP = tisc.exe_memo.size() - 1;
        // Cria um AL
        int new_AL = 0; //FIXME
        tisc.exe_memo.add(new_AL);
        // Cria um ER
        tisc.exe_memo.add(tisc.PC);

        // salta para a nova label
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
        tisc.exe_memo.add(integer1);
        tisc.exe_memo.add(integer2);

        int start = tisc.exe_memo.get(tisc.EP);

        start = start + 4 + tisc.exe_memo.get(start + 3) + tisc.exe_memo.get(start + 4);

        for(int i = 0; i < integer1 + integer2; i++){
            tisc.exe_memo.add(tisc.exe_memo.remove(start + i));
        }
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
        //TODO
    }

    @Override
    public String toString() {
        return "return";
    }
}

class Jump implements Instruction {
    String label;

    public Jump(String label) {
        super();
    }

    public void execute(TISC tisc) {
        tisc.PC = tisc.label_manager.get(label); //TEST
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
    }

    public void execute(TISC tisc) {
        int b = tisc.eval_stack.pop();
        int a = tisc.eval_stack.pop();

        if(a == b)
            tisc.PC = tisc.label_manager.get(label); //TEST
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
    }

    public void execute(TISC tisc) {
        int b = tisc.eval_stack.pop();
        int a = tisc.eval_stack.pop();

        if(a < b)
            tisc.PC = tisc.label_manager.get(label); //TEST
    }

    @Override
    public String toString() {
        return "jlt " + label;
    }
}

class Print implements Instruction {
    public Print() {
        super();
    }

    public void execute(TISC tisc) {
        int a = tisc.eval_stack.pop();

        System.out.print(a);
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
        System.out.print(string);
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
        System.out.println();
    }

    @Override
    public String toString() {
        return "print_nl";
    }
}
