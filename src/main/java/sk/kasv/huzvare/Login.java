package sk.kasv.huzvare;

import sk.kasv.huzvare.database.Database;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Login {
    private static final int BLOCKTIME = 300 ;//v sekundach
    Map<String,Integer> fails = new HashMap<>();
    Map<String, Date> block = new HashMap<>();

    public int loginUser(String username, String password){
        if(block.containsKey(username)){
            // uzivatel je blokovany, musime overit ci uplynul cas blokovania
            if(checkBlockTime(username))
            {
                // stale blokovany
                return 2;
            }else{
                // nie je uz blokovany
                block.remove(username);
            }
        }

        // uzivate nieje blokovany
        Database db = new Database();
        boolean ret = db.checkCredential(username,password);
        if(ret){
            // uspesne overenie hesla
            fails.remove(username);
            return 0;
        }else{
            // zle heslo alebo username
            if(fails.containsKey(username)){
                // uz ma min 1 zly pokus
                int value=fails.get(username);
                value++;
                fails.put(username, value);
                if(value==3){
                    block.put(username,new Date());
                    fails.remove(username);
                    return 2;
                }else{
                    return 1;
                }

            }else{
                // prvy krat zle credential
                fails.put(username,1);
                return 1;
            }
        }


    }

    private boolean checkBlockTime(String username) {
        Date currentDate= new Date();
        Date rec = block.get(username);
        if(currentDate.getTime()-rec.getTime()>=1000*BLOCKTIME) {
            return false;
        }
        else
            return true;
    }
}

