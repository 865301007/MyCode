class Solution {
    
public:
    int point_to_int(int r,int c)
    {
        int a=r/3;
        int b=c/3;
        return 3*a+b;
    }
    void init_map(vector<vector<char>>& board,vector<map<char,bool>> & r_map,vector<map<char,bool>> & c_map,vector<map<char,bool>> & s_map,vector<pair<int,int>> & space)
    {
 
        for(int r=0;r<9;r++)
        {
            for(int c=0;c<9;c++)
            {
                if(isdigit(board[r][c]))
                {
                r_map[r][board[r][c]]=1;
                c_map[c][board[r][c]]=1;
                s_map[point_to_int(r,c)][board[r][c]]=1;
                }
                else
                {
                    space.push_back(make_pair(r,c));
                }
                
            }
        }
    }
    
    bool trace_back(int n,vector<vector<char>>& board,vector<map<char,bool>> & r_map,vector<map<char,bool>> & c_map,vector<map<char,bool>> & s_map,vector<pair<int,int>> & space)
    {
        if(n>=space.size()) return true;
        int r=space[n].first;
        int c=space[n].second;
        for(char ch='1';ch<='9';ch++)
        {
            if(!c_map[c][ch]&&!r_map[r][ch]&&!s_map[point_to_int(r,c)][ch])
            {
                n++;
                c_map[c][ch]=1;
                r_map[r][ch]=1;
                s_map[point_to_int(r,c)][ch]=1;
                board[r][c]=ch;
                if(trace_back(n,board,r_map,c_map,s_map,space))
                    return true;
                 n--;
                c_map[c][ch]=0;
                r_map[r][ch]=0;
                s_map[point_to_int(r,c)][ch]=0;
                board[r][c]='.';
                
            }
        }
        return false;
    }
    
    void print_board(vector<vector<char>>& board)
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

    void solveSudoku(vector<vector<char>>& board) {
        if(board.size()!=9) return;
        map<char,bool> temp;
        for(char ch='1';ch<='9';ch++)
            temp[ch]=0;
        vector<map<char,bool>> r_map(9,temp);
        vector<map<char,bool>> c_map(9,temp);
        vector<map<char,bool>> s_map(9,temp);
        vector<pair<int,int>> space;
        init_map(board,r_map,c_map,s_map,space);
        trace_back(0,board,r_map,c_map,s_map,space);
       // print_board(board);
        
    }
};