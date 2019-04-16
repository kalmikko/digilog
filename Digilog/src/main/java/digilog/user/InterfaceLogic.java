/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digilog.user;

import java.io.File;
import org.springframework.stereotype.Component;

/**
 *
 * @author kalmikko
 */
@Component
public class InterfaceLogic {
    
    public boolean databaseFound(){
        File f = new File("./Digilog.mv.db");
        if(f.isFile()){
            return true;
        }
        return false;
    }
}
