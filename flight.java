import java.util.*;  // To incorporate Scanner class and ArrayList 

// Interface for displaying booking details, allows multiple inheritance which is otherwise not supported by Java
interface Display 
{
    void display();
}

// Parent class: FlightTicket
abstract class FlightTicket implements Display 
{
    protected String passengerName; //Protected helps regulate access by giving it to only the sub classes 
    protected String flightNumber;
    protected String destination;
    protected double baseFare;
    static int totalBookings = 0; // Static variable to track total bookings; Value doesn't change throughout

    // Constructor; same name as the class with no return type
    FlightTicket(String passengerName, String flightNumber, String destination, double baseFare) 
    {
        StringBuffer sb=new StringBuffer(passengerName); //Using StringBuffer 
        this.passengerName = sb.toString();
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.baseFare = baseFare;
        totalBookings++;
    }

    // Abstract method to calculate ticket price 
    abstract int calculatePrice();

    // Implementing the interface method
    public void display() 
    {
        System.out.println("Passenger: " + passengerName + ", Flight: " + flightNumber + 
                           ", Destination: " + destination + ", Ticket Price: " + calculatePrice());
    }
}

// Economy Class Ticket; derived from class FlightTicket
class EconomyTicket extends FlightTicket 
{
    private double discount = 0.1; //Private only has access in that particular class, therefore this discount is only for economy class

    // Constructor
    EconomyTicket(String passengerName, String flightNumber, String destination, double baseFare) {
        super(passengerName, flightNumber, destination, baseFare);
    }

    // Implementing abstract method
    int calculatePrice() 
    {
        return (int)(baseFare - (baseFare * discount)); //Explicit type casting
    }
}

// Business Class Ticket; derived from class FlightTicket
class BusinessTicket extends FlightTicket 
{
    private double luxuryTax = 0.25; //Extra charge only for business class ticket as it has private access

    // Constructor
    BusinessTicket(String passengerName, String flightNumber, String destination, double baseFare) 
    {
        super(passengerName, flightNumber, destination, baseFare);
    }

    // Implementing abstract method
    int calculatePrice() 
    {
        return (int)(baseFare + (baseFare * luxuryTax)); //Explicit type casting
    }
}
class FlightBookingWrapper //Using a wrapper class
{
    ArrayList<FlightTicket> bookings = new ArrayList<>();
    StringBuffer buffer = new StringBuffer(); //Using StringBuffer
    Scanner sc = new Scanner(System.in);
    public void startBookingSystem() 
    {
        while (true) 
        {
            System.out.println("\nFLIGHT BOOKING SYSTEM");
            System.out.println("1. Book Economy Ticket");
            System.out.println("2. Book Business Ticket");
            System.out.println("3. Display All Bookings");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();
            sc.nextLine(); 

            switch (ch) {
                case 1:
                    bookTicket(true);
                    break;
                case 2:
                    bookTicket(false);
                    break;
                case 3:
                    displayBookings();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    exitSystem();
                    return;
                default:
                    System.out.println("\nInvalid choice! Try again.");
            }
        }
    }

    void bookTicket(boolean isEconomy) //
    {
        System.out.print("\nEnter Passenger Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Flight Number: ");
        String flight = sc.nextLine();
        System.out.print("Enter Destination: ");
        String destination = sc.nextLine();
        System.out.print("Enter Base Fare: ");
        double fare = sc.nextDouble();
        FlightTicket ticket = isEconomy ? new EconomyTicket(name, flight, destination, fare): new BusinessTicket(name, flight, destination, fare);
        bookings.add(ticket);
        buffer.append(name).append(", ");
        System.out.println((isEconomy ? "Economy" : "Business") + " Ticket Booked Successfully!");
    }

    void displayBookings() 
    {
        System.out.println("\nALL BOOKINGS");
        if (bookings.isEmpty()) 
        {
            System.out.println("No bookings available.");
        } 
        else 
        {
            for (FlightTicket ticket : bookings) 
            {
                ticket.display();
            }
        }
    }

    private void cancelBooking() 
    {
        System.out.print("\nEnter Passenger Name to Cancel Booking: ");
        String cancelName = sc.nextLine();
        boolean removed = false;
        for (int i = 0; i < bookings.size(); i++) 
        {
            if (bookings.get(i).passengerName.contains(cancelName)) 
            {
                bookings.remove(i);
                System.out.println("Booking for " + cancelName + " has been cancelled.");
                removed = true;
                FlightTicket.totalBookings--; 
                break; 
            }
        }
        if (!removed) System.out.println("Booking not found.");
    }

    private void exitSystem() 
    {
        System.out.println("\nTotal Bookings: " + FlightTicket.totalBookings);
        System.out.println("Stored Bookings: " + buffer.toString()); 
        sc.close();
    }
}

// Main class
public class flight 
{
    public static void main(String[] args) 
    {
        FlightBookingWrapper bookingWrapper = new FlightBookingWrapper();
        bookingWrapper.startBookingSystem();
    }
}

            