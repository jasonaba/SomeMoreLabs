public class Highway {

	public static void main(String[] args) {
		/*System.out.println("Create all of the vehicles");
		Vehicle v = new Vehicle();
		System.out.println(v.toString());
		Vehicle h = new Helicopter("Heli", 5, 190, 3);
		System.out.println(h.toString());
		Vehicle c = new Car("Car", 4, 100);
		System.out.println(c.toString());
		Vehicle t = new Truck("Truck", 3, 110);
		System.out.println(t.toString());
		
		// Put all the vehicles in an array in the specified order
		Vehicle[] v_arr = {v, c, t, h};

		System.out.println("Increase Helicopter's max weight by 10K");
		((Helicopter) v_arr[3]).setMaxPassengerWeight(((Helicopter)v_arr[3]).getMaxPassengerWeight() + 10);
		System.out.println(v_arr[3].toString());// The value should increase by 50_000

		System.out.println("Tow the car");
		((Truck) v_arr[2]).tow(v_arr[1]);
		System.out.println(v_arr[1].toString());// should show it's scrap value and it's condition as 0
		// because 10% of the maximum value is 6_500, and the mileage depreciation would
		// make it decrease by 50_000, the scrap value will be $0
		
		System.out.println("Rebuild the vehicle as a helicopter"); 
		v_arr[0] = Helicopter.rebuild(v_arr[0]);//now v_arr[0] is pointing to a diff object, but v is not
		System.out.println(v_arr[0].toString());
		v = v_arr[0]; //to make them point to the same object

		System.out.println("Scrap the original helicopter");
		v_arr[3].setCondition(0);
		System.out.println(v_arr[3].toString());//should show Scrap Value and condition equal to 0
		
		System.out.println("Salvage the car and make it a golf cart");
		v_arr[1] = Car.salvage((Car)v_arr[1], "Golf Cart");//now the v_arr[1] is pointing at a different object, but the c variable is not
		System.out.println(v_arr[1].toString());//should show that it is now a vehicle
		c = v_arr[1];//to make them point at the same thing again
		
		System.out.println("Tow all the vehicles in a loop");
		for(int i = 0; i<v_arr.length;i++) {
			((Truck)t).tow(v_arr[i]);//tow each vehicle
			System.out.println(v_arr[i].toString());//show each vehicle that was towed
		}

		/*all tests
		System.out.println(v.toString());//when I was confused why it wasn't showing to be a helicopter
		System.out.println(c.toString());//when I was confused why it wasn't printing as a vehicle but as a car
		
		//to check it doesn't update trucks
		((Truck)t).tow(t);
		System.out.println(t.toString());
		
		
		//to make sure helicopter can rebuild any vehicle
		Vehicle v1 = new Vehicle("V-1", 4, 120);
		v1 = Helicopter.rebuild(v1);
		System.out.println(v1.toString());
		Vehicle c1 = new Car("C-1", 2, 180);
		c1 = Helicopter.rebuild(c1);
		System.out.println(c1.toString());
		Vehicle t1 = new Truck("T-1", 1, 140);
		t1 = Helicopter.rebuild(t1);
		System.out.println(t1.toString());
		
		
		//to make sure there are only 2 cars in total (the 1 car made and the 1 truck made)
		System.out.println(Car.getTotalCars());
		//to make sure there is only 1 active car (the truck) after the car is scrapped
		System.out.println(Car.getActiveCars());
		
		//to check if the truck will show a Scrap Value when it's condition equals 0
		Truck t2 = new Truck("T-2", 0, 10);//When mileage changes, it shows values different from 0 but also 0 when necessary (inherited from car class)
		System.out.println(t2.toString());
		
		
		//to make sure rebuilt helicopter shows correct maxPassengerWeight
		System.out.println(((Helicopter)v_arr[0]).getMaxPassengerWeight());
		
		//to make sure totalcars increases correctly and active cars decreases when a car's mileage exceeds 200K/condition = 0
		Car c3 = new Car("C-3", 4, 199);
		System.out.println(Car.getTotalCars());
		System.out.println(Car.getActiveCars());
		Truck t3 = new Truck("T-3", 2, 199);
		System.out.println(Car.getTotalCars());
		System.out.println(Car.getActiveCars());
		
		c3.setMiles(c3.getMiles()+1);
		System.out.println(Car.getActiveCars());
		t3.setMiles(t3.getMiles()+1);
		System.out.println(Car.getActiveCars());
		
		//tp see if scrapping/towing them also does it
		t3.setCondition(0);
		System.out.println(Car.getActiveCars());
		c3.setCondition(0); //t3.tow(c3);
		System.out.println(Car.getActiveCars());
		
		//to make sure truck depreciates correctly
		t.setMiles(t.getMiles()+10);
		System.out.println(t.toString());
		t.setMiles(t.getMiles()+10);
		System.out.println(t.toString());
		
		//to make sure car value is increasing by 1k per condition (compared with car at the top)
		Car c4 = new Car("Bomb", 5, 100);
		System.out.println(c4.toString());//it is following the (20_000+(8000*condition))+(1000*condition) rule perfectly
		*/
				// TODO Auto-generated method stub
				Vehicle[] fleet = new Vehicle[4];
				fleet[0] = new Vehicle("Motorcycle", 3, 10);
				fleet[1] = new Helicopter("Chopper", 5, 14, 50);
				
				fleet[2] = new Car("Kia", 4, 0);
				fleet[3] = new Truck("Tahoe", 2, 56);
				
				for (Vehicle Vehicle : fleet) {
					System.out.println(Vehicle);
					System.out.println();
				}

				System.out.println("Active: " + Car.getActiveCars());
				System.out.println("Total Count: " + Car.getTotalCars());

				fleet[1].setMiles(fleet[1].getMiles() + 80);
				System.out.println();
				((Truck) fleet[3]).tow(fleet[2]);
				System.out.println(fleet[2]);
				System.out.println("Towed the car. Active Count: " + Car.getActiveCars());
				System.out.println("Total Count: " + Car.getTotalCars());
				System.out.println();

				fleet[0] = Helicopter.rebuild(fleet[0]);
				System.out.println("Rebuilding the Motorcycle as a Helicopter");
				((Helicopter) fleet[0]).setMaxPassengerWeight(15);
				System.out.println(fleet[0]);
				System.out.println("Active Count: " + Car.getActiveCars());
				System.out.println("Total Count: " + Car.getTotalCars());
				System.out.println();
				
				
				System.out.println("Salvaging the Kia");
				fleet[2] = Car.salvage((Car)fleet[2], fleet[2].getName());fleet[2].setCondition(5);
				System.out.println(fleet[2]);

				System.out.println("Acive Count: " + Car.getActiveCars());
				System.out.println("Total Count: " + Car.getTotalCars());
				System.out.println();

				for (int i = 0; i < fleet.length; i++) {
					((Truck) fleet[3]).tow(fleet[i]);
					System.out.println();
				}
				System.out.println("Towed Everyone. Active Count: " + Car.getActiveCars());
				System.out.println("Total Count: " + Car.getTotalCars());
				System.out.println();

				for (Vehicle Vehicle : fleet) {
					System.out.println(Vehicle);
					System.out.println();
				}
				

				Vehicle Roman = new Car("Honda Accord", 5, 100);
				Roman.setMiles(210);Roman.setMiles(220);Roman.setCondition(0);
				
				System.out.println("Build Roman as a Broken Car");
				System.out.println(Roman);
				System.out.println("Active Final: " + Car.getActiveCars());
				System.out.println("Total Car: " + Car.getTotalCars());
				Roman = Car.salvage((Car)Roman, Roman.getName());
				Roman.setCondition(5);
				System.out.println("Salvage the Car as a GolfCart");
				System.out.println(Roman);
				System.out.println("Active Final: " + Car.getActiveCars());
				System.out.println("Total Car: " + Car.getTotalCars());
				
				System.out.println("Making a Condition 0 Truck");
				Truck Surg = new Truck("Surg",0,12);
				System.out.println(Surg);
				System.out.println("Reducing the miles to 0 and the value should be 2000");
				Surg.setMiles(0);
				System.out.println(Surg);

				System.out.println("Active Final: " + Car.getActiveCars());
				System.out.println("Total Car: " + Car.getTotalCars());
				
				System.out.println("Try to tow a null car");
				Surg.tow(null);
				
				
				
			}

	}

