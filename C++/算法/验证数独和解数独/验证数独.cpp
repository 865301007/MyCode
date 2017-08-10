class Solution {
public:
    bool check_colum(vector<vector<char>>& board,int c)
    {
        bool m[10]={0};
        for(int r=0;r<9;r++)
        {
            if(board[r][c]=='.') continue;
            if(m[board[r][c]-'0'])
                return false;
            m[board[r][c]-'0']=true;
        }
        return true;
    };
    bool check_raw(vector<vector<char>>& board,int r)
    {
            bool m[10]={0};
        for(int c=0;c<9;c++)
        {
            if(board[r][c]=='.') continue;
            if(m[board[r][c]-'0'])
                return false;
            m[board[r][c]-'0']=true;
        }
        return true;
    
        
    };
    bool check_square(vector<vector<char>>& board,int i,int j)
    {
        bool m[10]={0};
        int r=i;
        
        int maxr=i+3;
        int maxc=j+3;
        for(;r<maxr;r++)
        {
            int c=j;
            for(;c<maxc;c++)
            {
               // cout<<board[r][c]<<' ';
                 if(board[r][c]=='.') continue;
                 if(m[board[r][c]-'0'])
                   return false;
                 m[board[r][c]-'0']=true;
            }
           // cout<<endl;
        }
        return true;
    };
    void print(vector<vector<char>>& board)
    {
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                cout<<board[i][j]<<' ';
            }
            cout<<endl;
        }
    }
    bool isValidSudoku(vector<vector<char>>& board)
    {
        
        
        if(board.size()!=9) return false;
       // print(board);
        
        for(int i=0;i<9;i++)
        {
            
            if(board[i].size()!=9) return false;
            if(!check_colum(board,i)||!check_raw(board,i))
                return false;
        }
        for(int i=0;i<9;i+=3)
            for(int j=0;j<9;j+=3)
                if(!check_square(board,i,j))
                    return false;
                    
        return true;
    };
    

};