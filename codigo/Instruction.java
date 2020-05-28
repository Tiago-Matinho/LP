import java.util.*;

public interface Instruction {
    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP);
    public String toString();
}

class Add implements Instruction {
    public Add() {
        super();
    }

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        if(eval_stack.size() < 2)
            System.err.println("Not enough arguments to ADD\n");

        else{
            Integer b = eval_stack.pop();
            Integer a = eval_stack.pop();

            eval_stack.push(a + b);
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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        if(eval_stack.size() < 2)
            System.err.println("Not enough arguments to SUB\n");

        else{
            Integer b = eval_stack.pop();
            Integer a = eval_stack.pop();

            eval_stack.push(a - b);
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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        if(eval_stack.size() < 2)
            System.err.println("Not enough arguments to MULT\n");

        else{
            Integer b = eval_stack.pop();
            Integer a = eval_stack.pop();

            eval_stack.push(a * b);
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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        if(eval_stack.size() < 2)
            System.err.println("Not enough arguments to DIV\n");

        else{
            Integer b = eval_stack.pop();
            Integer a = eval_stack.pop();

            eval_stack.push(a / b);
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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        if(eval_stack.size() < 2)
            System.err.println("Not enough arguments to MOD\n");

        else{
            Integer b = eval_stack.pop();
            Integer a = eval_stack.pop();

            eval_stack.push(a % b);
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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        if(eval_stack.size() < 2)
            System.err.println("Not enough arguments to EXP\n");

        else{
            Integer b = eval_stack.pop();
            Integer a = eval_stack.pop();

            eval_stack.push(a ^ b);
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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        eval_stack.push(integer);
    }

    @Override
    public String toString() {
        return "push_int " + this.integer;
    }
}

class Push_var implements Instruction {
    int integer1, integer2;

    public Push_var(int integer1, int integer2) {
        super();
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        int temp = EP;

        for(int i = 0; i < integer1; i++) {
            temp = exe_memo.get(temp);
        }
        
        int value = exe_memo.get(temp + 2 + integer2);

        eval_stack.push(value);
    }

    @Override
    public String toString() {
        return "push_var";
    }
}

class Store_var implements Instruction {
    int integer1, integer2;

    public Store_var(int integer1, int integer2) {
        super();
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        int temp = EP;
        int value = eval_stack.pop();

        for(int i = 0; i < integer1; i++) {
            temp = exe_memo.get(temp);
        }

        exe_memo.set(temp + 2 + integer2, value);
    }

    @Override
    public String toString() {
        return "store_var";
    }
}

class Push_args implements Instruction {
    int integer1, integer2;

    public Push_args(int integer1, int integer2) {
        super();
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        int temp = EP;

        for(int i = 0; i < integer1; i++) {
            temp = exe_memo.get(temp);
        }

        int value = exe_memo.get(temp + 2 + integer2);

        eval_stack.push(value);
    }

    @Override
    public String toString() {
        return "push_args";
    }
}

class Store_args implements Instruction {
    int integer1, integer2;

    public Store_args(int integer1, int integer2) {
        super();
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        int temp = EP;
        int value = eval_stack.pop();

        for(int i = 0; i < integer1; i++) {
            temp = exe_memo.get(temp);
        }

        exe_memo.set(temp + 2 + integer2, value);
    }

    @Override
    public String toString() {
        return "store_args";
    }
}

class Set_arg implements Instruction {
    int integer1;

    public Set_arg(int integer1) {
        super();
        this.integer1 = integer1;
    }

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {
        int value = eval_stack.pop();

        exe_memo.set(EP + 2 + EP, value);
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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {

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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {

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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {

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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {

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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {

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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {

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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {

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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {

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

    public void execute(Stack<Integer> eval_stack, Vector<Integer> exe_memo, int EP) {

    }

    @Override
    public String toString() {
        return "print_nl";
    }
}
