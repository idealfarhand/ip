
package hamtaro.task;

/** Represents a task tracked by Hamtaro. */
public class Task {
        private String description;
        private boolean isDone;

        /** Creates an incomplete task with the given description. */
        public Task(String d){
            // Task descriptions come from validated commands or valid storage records.
            assert d != null && !d.isBlank() : "A task must have a description";
            this.description = d;
            this.isDone = false;
        }

        /** Creates a task with the given description and completion status. */
        public Task(String d, boolean i){
            // Restored tasks must satisfy the same invariant as newly created tasks.
            assert d != null && !d.isBlank() : "A task must have a description";
            this.description = d;
            this.isDone = i;
        }

        /** Marks this task as complete. */
        public void mark(){
            this.isDone = true;
        }
        /** Marks this task as incomplete. */
        public void unmark(){
            this.isDone = false;
        }

        /** Returns the symbol used to display this task's completion status. */
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

        /** Returns a displayable representation of this task. */
        @Override
        public String toString(){
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
}
