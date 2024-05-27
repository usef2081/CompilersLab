package csen1002.main.task2;
import java.util.*;

/**
 * Write your info here
 * 
 * @name Yousef Mohamed Hassan Mohamed
 * @id 49-0560
 * @labNumber 20
 */

public class NfaToDfa {

	private String Q;
	private String A;
	private String T;
	private String I;
	private String F;
	
	/**
	 * Constructs a DFA corresponding to an NFA
	 * 
	 * @param input A formatted string representation of the NFA for which an
	 *              equivalent DFA is to be constructed. The string representation
	 *              follows the one in the task description
	 */
	public NfaToDfa(String input) {
		// TODO Auto-generated constructor stub
		
		
		String [] nfa = input.split("#");

		String [] states0 = nfa[0].split(";");
		String [] eClosure0 = nfa[0].split(";");
		String [] symbols0 = nfa[1].split(";");
		String [] transitions0 = nfa[2].split(";");
		String initial_state = nfa[3];
		String [] accept_states0 = nfa[4].split(";");
		
		
		
		List<String> states = Arrays.asList(states0); 
		List<String> eClosure = Arrays.asList(eClosure0); 
		List<String> symbols = Arrays.asList(symbols0); 
		List<String> transitions = Arrays.asList(transitions0); 
		List<String> accept_states = Arrays.asList(accept_states0); 
		List<String> eClosure_tmp = new ArrayList<String>();
		
		
		
		
		
		//Filling E-closure table
		
		for (int i =0 ; i<transitions.size() ; i++) {
			
			String [] tmp = transitions.get(i).split(",");
			
			if(tmp[1].equals("e")) {
				
				String from = tmp[0];
				String to = tmp[2];
				
				
				int index = states.indexOf(from);
				
				if(!(eClosure.get(index).contains(to))) {
					eClosure.set(index, eClosure.get(index) + "/" +to);
					
				}
				
				
				
				
			}
			
			
			
		}

		
		
		for(int i=0; i<eClosure.size(); i++) {
			
			eClosure_tmp.add(eClosure.get(i));
			
			
		}
		
		
		
		boolean isChange = true;
		
		while(isChange) {
			
			isChange = false;
			
			for(int i =0 ; i<eClosure.size(); i++) {
				
				
				String current = eClosure.get(i);
				String [] current_arr = current.split("/");
				
				for(int j =0; j<current_arr.length ; j++) {
					
					int curr_index = states.indexOf(current_arr[j]);
					String [] current_arr2 = eClosure_tmp.get(curr_index).split("/");
					
					
					
					for(int k =0; k<current_arr2.length; k++) {
						
						String [] check = eClosure.get(i).split("/");
						List<String> check1 = Arrays.asList(check);
						
						
						
						if(!(check1.contains(current_arr2[k]))) {
							
							isChange= true;
							eClosure.set(i, eClosure.get(i) + "/" + current_arr2[k]);
							
							
						}
						
					}
					
					
					
					
				}
				
				
				
				
			}
			
			
			for(int i=0; i<eClosure.size(); i++) {
				
				eClosure_tmp.set(i, eClosure.get(i));
				
				
			}
			
			
			
			
			
			
			
		}
		
		
		
		//Sort eClosure table
		
		
		for(int i =0; i<eClosure.size(); i++) {
			
			String [] current = eClosure.get(i).split("/");
			int [] current_int = new int[current.length];
			
			
			for(int j =0; j<current.length; j++) {
				
				current_int[j] = Integer.parseInt(current[j]);
			}
			
			Arrays.sort(current_int);
			
			
			for(int j =0; j<current.length; j++) {
				
				current[j] = current_int[j] + "";
			}
			
			String newClosure = String.join("/", current);
			
			eClosure.set(i, newClosure);
			
			
			
			
		}
		
		
		
		
		
		int start_state_index = states.indexOf(initial_state);
		String start_state_final = eClosure.get(start_state_index);
		ArrayList<String> final_states = new ArrayList<String>();
		ArrayList<String> final_transitions = new ArrayList<String>();
		ArrayList<String> final_accept_states = new ArrayList<String>();
		
		final_states.add(start_state_final);
		
		
		
		
		//Identifying all transitions
		
		for(int i=0; i<final_states.size(); i++) {
			
			
			
			String [] current = final_states.get(i).split("/");
			
			
			for(int j =0; j<symbols.size(); j++) {
				
				String current_symbol = symbols.get(j);
				ArrayList <String> result_state = new ArrayList<String>();
				
				for(int k =0; k<current.length; k++) {
					 
					for(int m =0; m<transitions.size(); m++) {
						
						String current_transition0 = transitions.get(m);
						String [] current_transition = current_transition0.split(",");
						
						if(current_transition[0].equals(current[k])) {
							
							if(current_transition[1].equals(current_symbol)){
								
								String to = current_transition[2];
								
								int to_index = states.indexOf(to);
								String to_eClosure = eClosure.get(to_index);
								
								
								String [] tmp = to_eClosure.split("/");
								
								for(int t=0; t<tmp.length; t++) {
									
									if(!(result_state.contains(tmp[t]))) {
										
										result_state.add(tmp[t]);
									}
									
									
									
								}
								
							
								
							}
							
							
						}
						
						
						
						
						
					}
					
		
				}
				
				
				if(!(result_state.isEmpty())) {
					
					
					//Sort result_state before adding
					
					

						int [] current_int = new int[result_state.size()];
						
						
						for(int k =0; k<result_state.size(); k++) {
							
							current_int[k] = Integer.parseInt(result_state.get(k));
						}
						
						Arrays.sort(current_int);
						
						
						for(int kk =0; kk<result_state.size(); kk++) {
							
							result_state.set(kk, current_int[kk]+"");
						}
						
						
						
						
						
					
				
				
				String result_state_to_be_added = String.join("/", result_state);
				if(!(final_states.contains(result_state_to_be_added))) {
					
						final_states.add(result_state_to_be_added);
				}
				
				final_transitions.add(final_states.get(i) + "," + current_symbol + "," + result_state_to_be_added);
				
				
				
				}
				
				else {
					
					if(!(final_states.contains("-1"))) {
						
						final_states.add("-1");
					}
					
					final_transitions.add(final_states.get(i) + "," + current_symbol + ",-1");
					
					
				}
				
				
			}
			
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//Sort Final states table 
		
        final_states.sort((state1, state2) -> {
        	
        	
            String[] splitted_state1 = state1.split("/");
            
            String[] splitted_state2 = state2.split("/");
            
            int m = Math.min(splitted_state1.length, splitted_state2.length);
            
            for (int i = 0; i < m; i++) {
            	
                int item1 = Integer.parseInt(splitted_state1[i]);
                
                int item2 = Integer.parseInt(splitted_state2[i]);
                
                
                if (item1 != item2) {
                	
                    return Integer.compare(item1, item2);
                }
            }
            return Integer.compare(splitted_state1.length, splitted_state2.length);
        });
        
        
        
        //Sort Final Transitions Table
		
        final_transitions.sort((transition1, transition2) -> {
        	
        	
            String[] splitted_transition01 = transition1.split(",");
            String[] splitted_transition1 = splitted_transition01[0].split("/");
            
            String[] splitted_transition02 = transition2.split(",");
            String[] splitted_transition2 = splitted_transition02[0].split("/");
            
            int m = Math.min(splitted_transition1.length, splitted_transition2.length);
            
            for (int i = 0; i < m; i++) {
            	
                int item1 = Integer.parseInt(splitted_transition1[i]);
                
                int item2 = Integer.parseInt(splitted_transition2[i]);
                
                
                if (item1 != item2) {
                	
                    return Integer.compare(item1, item2);
                }
            }
            return Integer.compare(splitted_transition1.length, splitted_transition2.length);
        });
		
		
        //Identifying Accept States
        
        for(int i=0; i<final_states.size(); i++) {
        	
        	boolean accept = false;
        	
        	String [] current_state = final_states.get(i).split("/");
        	
        	List<String> splitted_state = Arrays.asList(current_state);
        	
        	for(int j =0; j<accept_states.size(); j++) {
        		
        		if(splitted_state.contains(accept_states.get(j))) {
        			
        			
        			accept = true;
        			break;
        			
        		}
        		
        		
        		
        	}
        	
        	if(accept) {
        		
        		final_accept_states.add(final_states.get(i));
        		
        	}
        	
        	
        	
        	
        	
        	
        	
        }
        
		String string_final_states = String.join( ";", final_states);
		String string_final_input_alphabet = String.join( ";", symbols);
		String string_final_transitions = String.join( ";", final_transitions);
		String string_final_accept_states = String.join( ";", final_accept_states);
        
        
        
		this.Q = string_final_states;
		this.A = string_final_input_alphabet;
		this.T = string_final_transitions;
		this.I = start_state_final;
		this.F = string_final_accept_states;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * @return Returns a formatted string representation of the DFA. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
	
		String result = this.Q + "#" + this.A + "#" + this.T + "#" + this.I + "#" + this.F ;
		
		return result;
	}
	
	
	
}
