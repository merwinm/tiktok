package game;

public class Tiktok {

    private int tikarea[][];
    private int SIZE = 3;
    private int O = 1;
    private int X = 2;

    Tiktok() {
        tikarea = new int[SIZE][SIZE];
    }

    public int startGame() {
        while(true){
            if(check(1))
                return 1;

            if(check(2))
                return 2;
        }
    }

    public int[][] getfield() {
        return this.tikarea;
    }

    public void setX(int x, int y,int value) {
        this.tikarea[x][y] = value;
    }

    public void setO(int x, int y, int value) {
        this.tikarea[x][y] = value;
    }

    public boolean check(int input) {
        if(this.tikarea[0][0] == input && this.tikarea[1][1] == input && this.tikarea[2][2] == input){
            return true;
        }
        if(this.tikarea[0][1] == input && this.tikarea[1][1] == input && this.tikarea[2][1] == input){
            return true;
        }


        if(this.tikarea[0][0] == input && this.tikarea[0][1] == input && this.tikarea[0][2] == input){
            return true;
        }
        if(this.tikarea[1][0] == input && this.tikarea[1][1] == input && this.tikarea[1][2] == input){
            return true;
        }
        if(this.tikarea[2][0] == input && this.tikarea[2][2] == input && this.tikarea[2][2] == input){
            return true;
        }

        if(this.tikarea[0][0] == input && this.tikarea[1][0] == input && this.tikarea[2][0] == input){
            return true;
        }
        if(this.tikarea[0][1] == input && this.tikarea[1][1] == input && this.tikarea[2][1] == input){
            return true;
        }
        if(this.tikarea[0][2] == input && this.tikarea[1][2] == input && this.tikarea[2][2] == input){
            return true;
        }
        else
            return false;
}

    public boolean fullfields() {
        return false;
    }

    public String toString(){
        String output = "";
        for(int x = 0; x < this.SIZE; x++){
            for(int y = 0; y < this.SIZE; y++){
                    System.out.println(this.tikarea[x][y] + ":");
                    output = this.tikarea[x][y] + " : ";
            }
        }
        return output;
    }
}
