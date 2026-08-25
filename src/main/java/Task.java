public class Task {
        private String description;
        private boolean isDone;

        public Task(String d, boolean i){
            this.description = d;
            this.isDone = i;
        }

        public String getDescription() {
            return description;
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
}
