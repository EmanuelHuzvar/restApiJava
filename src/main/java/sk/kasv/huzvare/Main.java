package sk.kasv.huzvare;

import sk.kasv.huzvare.database.Database;

public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        db.checkCredential("Admin","Roman123");
    }
}
