
package hamtaro.task;

/** Represents a task tracked by Hamtaro. */
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

        /** Returns the task description for persistent storage. */
        public String getDescription() {
            return description;
        }

        /** Returns whether this task has been completed. */
        public boolean isDone() {
            return isDone;
        }

        @Override
        public String toString(){
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
}
