package DAO;

/**
 * Created by Зая on 05.07.2016.
 */
public class Main2 {
    public static void main(String[] args) {

        //create initial JdbcPersonDao
        //   JdbcPersonDao personDao = new JdbcPersonDao();
//        personDao.createTable();
//
//        Client client = new Client("Maria", "Gahova", "c.Kiev,Lomonosova st. 33,fl.2", "+380991299729", "gahova@gmail.com");
//        personDao.insert(client);
//
//        Client client2 = new Client("Ann", "Gahova", "c.Kiev,Lomonosova st. 33,fl.2", "+380991299729", "gahova@gmail.com");
//        personDao.update(client2, 1);
//
        //    Client client3 = new Client("Oleg", "Jazuk", "c.Kiev,Lomonosova st. 33,fl.2", "+380991299729", "jazuk@gmail.com");
//        personDao.insert(client3);
//        personDao.delete(6);
//        Client client4 = new Client("Olga", "Rebryk", "c.Kiev,Lomonosova st. 33,fl.2", "+380991299729", "rebryk@gmail.com");
//        personDao.insert(client4);
//
//        Client client5 = personDao.find(2);
//        System.out.println(client5);
//
//        List list = personDao.findAll();
//        System.out.println(list);

        //create initial JdbcApartmentDao
    //      JdbcApartmentDao apartmentDao = new JdbcApartmentDao();
        // apartmentDao.createTable();

//
//      Apartment apartment1=new Apartment(Category.SEMIDELUXE,400,true,1,1,true,true);
//      apartmentDao.insert(apartment1);

//        Apartment apartment2=new Apartment(Category.SEMIDELUXE,500,true,2,2,true,true);
//        apartmentDao.update(apartment2,1);

//       Apartment apartment3=new Apartment(Category.ECONOM,300,true,2,2,true,true);
//       apartmentDao.insert(apartment3);
        //  apartmentDao.delete(2);

//        Apartment apartment4=  apartmentDao.find(3);
//        System.out.println(apartment4);

//        List aparts=apartmentDao.findAll();
//        System.out.println(aparts);


        //create initial JdbcBookingApplic
        //     JdbcBookingApplic applicationDao = new JdbcBookingApplic();
        //  applicationDao.createTable();
//        Application application1 = new Application(personDao.find(2), "none", 4, 2, Category.DELUXE,
//                LocalDate.of(2016, 12, 20), LocalDate.of(2016, 12, 29));
//        applicationDao.insert(application1);

//        applicationDao.update(new Application(personDao.find(3), "none", 4, 2, Category.ECONOM,
//                LocalDate.of(2016, 12, 20), LocalDate.of(2016, 12, 29)), 1);
//        Application application2 = new Application(personDao.find(2), "none", 4, 2, Category.DELUXE,
//                LocalDate.of(2016, 12, 20), LocalDate.of(2016, 12, 29));
//       applicationDao.insert(application2);
        // applicationDao.delete(2);
//      Application application=  applicationDao.find(3);
//        System.out.println(application);
//        List list = applicationDao.findAll();
//        System.out.println(list);

        //create initial
        JDBCReservation reservationDao=new JDBCReservation();
//    //  reservationDao.createTable();
//      Apartment apartment=new JdbcApartmentDao().find(4);
//        Client client=new JdbcPersonDao().find(2);
//       Reservation reservation1=new Reservation(apartment,client, LocalDate.of(2016, 12, 15), LocalDate.of(2016, 12, 31),true);
//       reservationDao.insert(reservation1);

        System.out.println(reservationDao.findAll());
    }
}
