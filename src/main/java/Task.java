public class Task {
        private String description;
        private boolean isDone;

        public Task(String d){
            this.description = d;
            this.isDone = false;
        }

        public Task(String d, boolean i){
            this.description = d;
            this.isDone = i;
        }


        public void mark(){
            this.isDone = true;
        }
        public void unmark(){
            this.isDone = false;
        }

        public String getStatusIcon(){
            return (isDone? "X" : " ");
        }

        @Override
        public String toString(){
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
}
