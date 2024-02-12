package vlille;

/**
 * Abstract class Vehicle
 */
public abstract class Vehicle {

        /** the id */
        protected String id;
        /** the state */
        protected VehicleState state;
        /** the color */
        protected Color color;
        /** the size */
        protected Size size;
        /** the number of rentals */
        protected int numRentals = 0;
        /** the number of intervals alone */
        protected int numIntervalsAlone = 0;
        /** the number of repair intervals */
        protected int numRepairIntervals = 0;

        /**
         * Constructor of Vehicle
         * @param id : id of the vehicle
         * @param color : color of the vehicle
         * @param size : size of the vehicle
         * @param state : state of the vehicle
         */
        protected Vehicle(String id, Color color, Size size, VehicleState state) {
                this.id = id;
                this.color = color;
                this.size = size;
                this.state = state;
        }

        /**
         * getter of Id
         * 
         * @return id of the vehicle
         */
        public String getId() {
                return this.id;
        }

        /**
         * repair the vehicle
         */
        public void repair() {
                this.state = VehicleState.GOOD;
        }

        /**
         * getter of color
         * 
         * @return color of the vehicle
         */
        public Color getColor() {
                return this.color;
        }

        /**
         * getter of state
         * 
         * @return state of the vehicle
         */
        public VehicleState getState() {
                return this.state;
        }

        /**
         * setter of id
         * 
         * @param id : id of the vehicle
         */
        public void setId(String id) {
                this.id = id;
        }

        /**
         * setter of the vehicle color
         * 
         * @param color : color of the vehicle
         */
        public void setColor(Color color) {
                this.color = color;
        }

        /**
         * setter of state
         * 
         * @param state : state of the vehicle
         */
        public void setState(VehicleState state) {
                this.state = state;
        }

        /**
         * getter of size
         * 
         * @return the size : size of the vehicle
         */
        public Size getSize() {
                return size;
        }

        /**
         * setter of size
         * 
         * @param size the size to set
         */
        public void setSize(Size size) {
                this.size = size;
        }

        /**
         * getter of numRentals
         * 
         * @return the numRentals : number of rentals of the vehicle
         */
        public int getNumRentals() {
                return this.numRentals;
        }

        /**
         * increment the number of rentals
         */
        public void incrementNumRentals() {
                this.numRentals++;
        }

        /**
         * reset the number of rentals
         */
        public void resetNumRentals() {
                this.numRentals = 0;
        }

        /**
         * getter of numIntervalsAlone
         * 
         * @return the numIntervalsAlone : number of intervals alone of the vehicle
         */
        public int getNumIntervalsAlone() {
                return this.numIntervalsAlone;
        }

        /**
         * increment the number of intervals alone
         */
        public void incrementNumIntervalsAlone() {
                this.numIntervalsAlone++;
        }

        /**
         * reset the number of intervals alone
         */
        public void resetNumIntervalsAlone() {
                this.numIntervalsAlone = 0;
        }

        /**
         * getter of numRepairIntervals
         * 
         * @return the numRepairIntervals : number of repair intervals of the vehicle
         */
        public int getNumRepairIntervals() {
                return this.numRepairIntervals;
        }

        /**
         * increment the number of repair intervals
         */
        public void incrementNumRepairIntervals() {
                this.numRepairIntervals++;
        }

        /**
         * reset the number of repair intervals
         */
        public void resetNumRepairIntervals() {
                this.numRepairIntervals = 0;
        }

        /**
         * add an option to the vehicle
         * 
         * @return the option added
         */
        public abstract String addOption();
}
