public class DatabaseConstructor {
    public static void main(String[] args) {

        GarageSaleDatabase GSDB = new GarageSaleDatabase();
        
        GSDB.createTable(GSDB.customersTable);
        GSDB.createTable(GSDB.showroomsTable);
        GSDB.createTable(GSDB.brandsTable);
        GSDB.createTable(GSDB.dealersTable);
        GSDB.createTable(GSDB.vehiclesTable);
        GSDB.createTable(GSDB.salesTable);
        
        GSDB.insertShowroomsData("ON", "ANKARA", "0555 265 8888");
        GSDB.insertShowroomsData("ON", "ISTANBUL", "0555 222 6677");
        GSDB.insertShowroomsData("OFF", "ISTANBUL", "0525 333 6677");

        GSDB.insertBrandsData("AUDI", "GERMANY ","0312 583 8383");
        GSDB.insertBrandsData("BMW", "GERMANY", "0312 909 3434");
        GSDB.insertBrandsData("TOYOTA", "JAPAN", "0312 342 0428");

    }
}
