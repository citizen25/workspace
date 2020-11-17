class Tetris{
    constructor(map){

        this.map = map;
        this.i_block = [[1,1,1,1]];
        this.o_block = [[1,1],[1,1]];
        this.t_block = [[0,1,0],[1,1,1]];
        this.l_block = [[0,0,1],[1,1,1]];
        this.j_block = [[1,0,0],[1,1,1]];
        this.s_block = [[0,1,1],[1,1,0]];
        this.z_block = [[1,1,0],[0,1,1]];
        
    }

    tick(){
        document.body.addEventListener("keydown", function(){
            console.log("keydown");

            console.log(map[0][1]);

            // for(var i=0; i<this.map.length; i++){
            //     for(var j=0; j<this.map[0].length; j++){
            //         if(this.map[i][j] == 0){
                        
            //         }
            //     }
            // }
        });
    }
}