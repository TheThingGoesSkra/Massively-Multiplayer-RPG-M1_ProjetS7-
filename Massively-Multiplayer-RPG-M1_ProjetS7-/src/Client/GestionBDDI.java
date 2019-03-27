package Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionBDDI {

        private String url;
        List<List<String>> result;

        //Constructor
        public GestionBDDI(String url){
            this.url = url;
            this.result = new ArrayList<>();
        }

        //Getter and setter
        public String getUrl() {
            return this.url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public List<List<String>> getResult() {
            List<List<String>> result= new ArrayList<>();
            result.addAll(this.result);
            return result;
        }

        //Methods
        public synchronized List<List<String>> requete(String requete, int mode){
            try {
                this.result.clear();
                Connection conn = DriverManager.getConnection(this.url);

                //Creation d'un objet Statement
                Statement state = conn.createStatement();
                switch(mode){
                    case 0:
                        //L'objet ResultSet contient le resultat de la requete SQL
                        ResultSet result = state.executeQuery(requete);
                        //On recupere les MetaData
                        ResultSetMetaData resultMeta = result.getMetaData();

                        List<String> temp;
                        while (result.next()) {
                            temp = new ArrayList<>();
                            for (int i = 1; i <= resultMeta.getColumnCount(); ++i)
                                temp.add(result.getObject(i).toString());
                            this.result.add(temp);
                        }

                        result.close(); // Chaque requete
                        break;
                    case 1:
                        state.executeUpdate(requete);
                        break;
                    case 2:
                        state.execute(requete);
                        break;
                }
                state.close(); // A la fin
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<List<String>> tempResult = new ArrayList<>();
            tempResult.addAll(this.result);
            return tempResult;
        }
        public List<List<String>> requeteSelect(String select, String from, String where){
            String requete = "";
            requete = "SELECT DISTINCT "+select+" FROM "+from;
            if(where!="")
                requete += " WHERE "+where;
            return this.requete(requete,0);
        }
        public List<List<String>> requeteInsertInto(String insertInto, String values, String where){
            String requete = "";
            requete = "INSERT INTO "+insertInto+" VALUES "+values;
            if(where!="")
                requete += " WHERE "+where;
            return this.requete(requete,1);
        }
        public List<List<String>> requeteUpdate(String nomTable, String colonnes, String where){
            String requete = "";
            requete = "UPDATE "+nomTable+" SET "+colonnes;
            if(where!="")
                requete += " WHERE "+where;
            return this.requete(requete,1);
        }
        public List<List<String>> requeteCreateTable(String nomTable, String nomColonnes_types){
            String requete = "";
            requete = "CREATE FROM "+nomTable+" ("+nomColonnes_types+")";
            return this.requete(requete,1);
        }

        public void requeteDelete(String nomTable, String where){
            String requete = "DELETE FROM "+nomTable+" WHERE "+where;
            this.requete(requete,2);
        }

        public void printResultData(){
            System.out.println(this.result);
        }

        /* test
        public static void main(String[] args){
            String url = "jdbc:sqlite:projets7.sqlite";
            GestionBDDI myBDD = new GestionBDDI(url);
            myBDD.requete("SELECT * FROM hall",0);
            myBDD.printResultData();
        }*/
}
