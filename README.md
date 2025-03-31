# Hotel-Reservation-System
Hotel Reservation System designed to be used for managing all kinds of reservation requests. This can be deployed by hotels/restaurants at the backend 

Technical Specifications:
We have used MYSql database to store and retrieve the reservation data and Java for backend programming. JDBC is used to interact between Java and database.
The database 'Hotel' has a table 'Reservations' which stores the following information - 
  - reservation_id which is auto-incremented
  - guest_name
  - room_number
  - contact_number
  - timestamp which will be auto set to current time


The system provides the following features:
1. Reserve a room: A new reservation can be done entering details like guest name, room number and contact number.
2. View Reservations: This option can be used when all the current reservations need to be viewed/accessed.
3. Get Room Number: A user can fetch room number of a particular guest by entering his/her reservation ID and name.
4. Update Reservations: The details of the guest such as name, room number and contact number can be updated by searching a particular guest using his/her reservation ID.
5. Delete Reservations: A particular entry of a guest can be deleted if required using the reservation ID.
