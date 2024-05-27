package csen1002.main.task3;

import java.util.*;


/**
 * Write your info here
 * 
 * @name Yousef Mohamed Hassan Mohamed
 * @id 49-0560
 * @labNumber 20
 */

public class FallbackDfa {
	
    List<String> Q;
	List<String> A;
	List<List<String>> T;
	String I;
	List<String> F;

	/**
	 * Constructs a Fallback DFA
	 * 
	 * @param fdfa A formatted string representation of the Fallback DFA. The string
	 *             representation follows the one in the task description
	 */
	public FallbackDfa(String fdfa) {
		// TODO Auto-generated constructor stub
		
		List<List<String>> transitions = new ArrayList<List<String>>();
		
		String [] nfa = fdfa.split("#");

		String [] states0 = nfa[0].split(";");
		String [] symbols0 = nfa[1].split(";");
		String [] transitions0 = nfa[2].split(";");
		
		for(int i =0; i<transitions0.length; i++) {
			
			String [] tmp = transitions0[i].split(",");
			List<String> tmp1 = Arrays.asList(tmp);
			transitions.add(tmp1);
			
			
		}
		String initial_state = nfa[3];
		String [] accept_states0 = nfa[4].split(";");
		
		
		
		List<String> states = Arrays.asList(states0);  
		List<String> symbols = Arrays.asList(symbols0); 
		List<String> accept_states = Arrays.asList(accept_states0); 
		
		
		this.Q = states;
		this.A = symbols;
		this.T = transitions;
		this.I = initial_state;
		this.F = accept_states;
		
		
	}

	/**
	 * @param input The string to simulate by the FDFA.
	 * 
	 * @return Returns a formatted string representation of the list of tokens. The
	 *         string representation follows the one in the task description
	 */
	public String run(String input) {
		
		
		
		List<String> result = new ArrayList<String>();
		String [] splitted_input0 = input.split("");
		
		List<String> splitted_input = Arrays.asList(splitted_input0);
		
		int length = input.length();
		
		int current_start = 0;                     //R
		int movingEnd = 0;                         //L
		String current_state;
		
		
		
		
		
		
		
		
		while(current_start< length) {
			
			current_state = this.I;
			Stack<String> states_stack = new Stack<String>();
			
			
			
			states_stack.push(this.I);
			
			 for(int i= current_start; i<length; i++) {
				 
				 String current_symbol = splitted_input.get(i);
				 
				 for(int j =0; j<this.T.size(); j++) {
					 
					 if(T.get(j).get(0).equals(current_state)) {
						 
						 if(T.get(j).get(1).equals(current_symbol)) {
							 
							 states_stack.push(T.get(j).get(2));
							 current_state = T.get(j).get(2);
							 
							 
							 break;
						 }
						 
					 } 
				 }
			 }
			 
			 
			 
			 movingEnd = length;
			 boolean acceptedFound = false;
			 int possible =0;
			 possible = movingEnd - 1;
			 String possible_state = "";
			 possible_state = states_stack.peek();
			 String accepted_popped_state = "";
			 
			 
			 
			 while( (!states_stack.isEmpty()) && (!acceptedFound)) {
				 
				 if(current_start == 0) {
				 
				 }
				 
				 
				 
				 String popped_state = states_stack.pop();
				 movingEnd--;
				 
				 
				 if(this.F.contains(popped_state) ) {
					 
					 acceptedFound = true;
					 accepted_popped_state = popped_state;
				 }
				 
				 
				 
			 }
			 
			 
			 if(acceptedFound) {
				 
				 result.add(input.substring(current_start, movingEnd+1) + "," + accepted_popped_state);
				 states_stack.clear();
				 movingEnd++;
				 
				 
				 
			 }
			 
			 
			 else {
				 
				 
				 
				 result.add(input.substring(current_start, possible+1) + "," + possible_state);
				 movingEnd = possible+1;
				 
				 
			 }
			 
			
			 current_start = movingEnd;
			 
			 
			 
			
			
			
		}
		return String.join(";", result);
		
		// TODO Auto-generated method stub
		
	}
	
	
	
}
