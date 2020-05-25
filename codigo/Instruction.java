import java.util.Stack;

public class Instruction {
    public enum Inst_type {
        ARITMETIC,
        MANIPULATE_INT,
        ACCESS_VARS,
        ACCESS_ARGS,
        FUNCT_CALL,
        JUMP,
        EXIT
    }

    Inst_type type;

    public Instruction() {
    }
}

class Aritmectic_inst extends Instruction {
    public enum Kind {
        ADD,
        SUB,
        MULT,
        DIV,
        MOD,
        EXP
    }

    Kind kind;

    public Aritmectic_inst(String kind) {
        super();
        this.type = Inst_type.ARITMETIC;

        switch(kind) {
            case "ADD":
                this.kind = Kind.ADD;
                break;

            case "SUB":
                this.kind = Kind.SUB;
                break;

            case "MULT":
                this.kind = Kind.MULT;
                break;

            case "DIV":
                this.kind = Kind.DIV;
                break;

            case "MOD":
                this.kind = Kind.MOD;
                break;

            case "EXP":
                this.kind = Kind.EXP;
                break;

            default:
                System.err.println("Error: wrong aritmectic kind");
                System.exit(1);
                break;
        }
    }

    @Override
    public String toString() {
        String ret;
        switch(this.kind){
            case ADD:
                ret = "add";
                break;

            case SUB:
                ret = "sub";
                break;

            case MULT:
                ret = "mult";
                break;

            case DIV:
                ret = "div";
                break;

            case MOD:
                ret = "mod";
                break;

            case EXP:
                ret = "exp";
                break;
                
            default:
                ret = "Something went wrong";
        }
        return ret;
    }
}

class Manipulate_int_inst extends Instruction {
    String integer;

    public Manipulate_int_inst(String integer) {
        super();
        this.type = Inst_type.MANIPULATE_INT;
        this.integer = integer;
    }

    @Override
    public String toString() {
        return "push_int " + this.integer;
    }
}

class Access_vars_inst extends Instruction {
    public enum Kind {
        PUSH,
        STORE
    }

    Kind kind;
    String integer1, integer2;

    public Access_vars_inst(String kind, String integer1, String integer2) {
        super();
        this.type = Inst_type.ACCESS_VARS;
        switch(kind) {
            case "PUSH":
                this.kind = Kind.PUSH;
                break;

            case "STORE":
                this.kind = Kind.STORE;
                break;

            default:
                System.err.println("Error: wrong access vars kind");
                System.exit(1);
                break;
        }
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    @Override
    public String toString() {
        String ret;
        switch(this.kind){
            case PUSH:
                ret = "push_arg";
                break;

            case STORE:
                ret = "store_arg";
                break;

            default:
                ret = "Something went wrong";
        }
        return ret + " " + this.integer1 + " " + this.integer2;
    }
}

class Access_args_inst extends Instruction {
    public enum Kind {
        PUSH,
        STORE
    }
    Kind kind;
    String integer1, integer2;

    public Access_args_inst(String kind, String integer1, String integer2) {
        super();
        this.type = Inst_type.ACCESS_ARGS;
        switch(kind) {
            case "PUSH":
                this.kind = Kind.PUSH;
                break;

            case "STORE":
                this.kind = Kind.STORE;
                break;

            default:
                System.err.println("Error: wrong access args kind");
                System.exit(1);
                break;
        }
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    @Override
    public String toString() {
        String ret;
        switch(this.kind){
            case PUSH:
                ret = "push_var";
                break;

            case STORE:
                ret = "store_var";
                break;

            default:
                ret = "Something went wrong";
        }
        return ret + " " + this.integer1 + " " + this.integer2;
    }
}

class Funct_call_inst extends Instruction {
    public enum Kind {
        SET,
        CALL,
        LOCALS,
        RET
    }
    Kind kind;
    String integer1, integer2;
    String label;

    public Funct_call_inst(String kind, String integer1, String integer2, String label) {
        super();
        this.type = Inst_type.FUNCT_CALL;
        switch(kind) {
            case "SET":
                this.kind = Kind.SET;
                break;

            case "CALL":
                this.kind = Kind.CALL;
                break;

            case "LOCALS":
                this.kind = Kind.LOCALS;
                break;

            case "RET":
                this.kind = Kind.RET;
                break;

            default:
                System.err.println("Error: wrong funct call kind");
                System.exit(1);
                break;
        }
        this.integer1 = integer1;
        this.integer2 = integer2;
        this.label = label;
    }

    @Override
    public String toString() {
        String ret;
        switch(this.kind){
            case SET:
                ret = "set_arg";
                ret += " " + integer1;
                break;

            case CALL:
                ret = "call";
                ret += " " + integer1 + " " + label;
                break;

            case LOCALS:
                ret = "locals";
                ret += " " + integer1 + " " + integer2;
                break;

            case RET:
                ret = "return";
                break;
                
            default:
                ret = "Something went wrong";
        }
        return ret;
    }
}

class Jump_inst extends Instruction {
    public enum Kind {
        JUMP,
        JEQ,
        JLT
    }
    Kind kind;
    String label;
    
    public Jump_inst(String kind, String label) {
        super();
        this.type = Inst_type.JUMP;
        switch(kind) {
            case "JUMP":
                this.kind = Kind.JUMP;
                break;

            case "JEQ":
                this.kind = Kind.JEQ;
                break;
           
            case "JLT":
                this.kind = Kind.JLT;
                break;

            default:
                System.err.println("Error: wrong jump kind");
                System.exit(1);
                break;
        }
        this.label = label;
    }

    @Override
    public String toString() {
        String ret;
        switch(this.kind){
            case JUMP:
                ret = "jump";
                break;

            case JEQ:
                ret = "jeq";
                break;

            case JLT:
                ret = "jlt";
                break;
                
            default:
                ret = "Something went wrong";
        }
        return ret + " " + label;
    }
}

class Exit_inst extends Instruction {
    public enum Kind {
        PRINT,
        PRINT_STR,
        PRINT_NL
    }
    Kind kind;
    String string;
    
    public Exit_inst(String kind, String string) {
        super();
        this.type = Inst_type.EXIT;
        switch(kind) {
            case "PRINT":
                this.kind = Kind.PRINT;
                break;

            case "PRINT_STR":
                this.kind = Kind.PRINT_STR;
                break;
           
            case "PRINT_NL":
                this.kind = Kind.PRINT_NL;
                break;

            default:
                System.err.println("Error: wrong exit kind");
                System.exit(1);
                break;
        }
        this.string = string;
    }

    @Override
    public String toString() {
        String ret;
        switch(this.kind){
            case PRINT:
                ret = "print";
                break;

            case PRINT_STR:
                ret = "print_str";
                ret += " " + string;
                break;

            case PRINT_NL:
                ret = "print_nl";
                break;
                
            default:
                ret = "Something went wrong";
        }
        return ret;
    }
}

