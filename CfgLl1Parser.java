package csen1002.main.task8;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Yousef Mohamed Hassan Mohamed
 * @id 49-0560
 * @labNumber 20
 */

public class CfgLl1Parser {

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG, the First sets of
	 *            each right-hand side, and the Follow sets of each variable. The
	 *            string representation follows the one in the task description
	 */
	
	
	List<String> V;
	List<String> T;
	ArrayList<List<String>>  R;
	ArrayList<List<String>> firsts;
	List<String> follows;
	ArrayList<List<String>> predictive_parsing_table;
	
	
	
	public CfgLl1Parser(String input) {
		// TODO Auto-generated constructor stub
		
		String [] tmp = input.split("#");
		
		String [] variables0 = tmp[0].split(";");
		String [] terminals0 = tmp[1].split(";");
		String [] rules0 = tmp[2].split(";");
		String [] firsts0 = tmp[3].split(";");
		String [] follows0 = tmp[4].split(";");
		
		ArrayList<String> variables = new ArrayList<String>();
		ArrayList<String> terminals = new ArrayList<String>();
		ArrayList<String> follows = new ArrayList<String>();
		
		
		for(int i =0; i<variables0.length; i++) {
			
			variables.add(variables0[i]);
			
		}
		
		for(int i =0; i<terminals0.length; i++) {
			
			terminals.add(terminals0[i]);
			
		}
		
		for(int i =0; i<follows0.length; i++) {
			
			String t = follows0[i].substring(2);
			
			follows.add(t);
			
		}
		
		ArrayList<List<String>> rules00 = new ArrayList<List<String>>();
		
		
		
		
		for(int i=0 ; i<rules0.length; i++) {
			
			String temp = rules0[i].substring(2);
			String [] temp1 = temp.split(",");
			List<String> temp2 = new ArrayList<String>();
			rules00.add(temp2);
			
			for(int j =0; j<temp1.length; j++) {
				
				
				rules00.get(i).add(temp1[j]);
			}
			
			
			
		}
		
		
		
		ArrayList<List<String>> firsts00 = new ArrayList<List<String>>();
		
		
		for(int i=0 ; i<firsts0.length; i++) {
			
			String temp = firsts0[i].substring(2);
			String [] temp1 = temp.split(",");
			List<String> temp2 = new ArrayList<String>();
			firsts00.add(temp2);
			
			for(int j =0; j<temp1.length; j++) {
				
				
				firsts00.get(i).add(temp1[j]);
			}
			
			
			
		}
		
		
		
		
		this.V = variables;
		this.T = terminals;
		this.R = rules00;
		this.firsts = firsts00;
		this.follows = follows;
		
		
		
		
		
		
		
		
	}

	/**
	 * @param input The string to be parsed by the LL(1) CFG.
	 * 
	 * @return A string encoding a left-most derivation.
	 */
	
	
	
	public void create_predictive_parsing_table() {
		
		
		//Initializing table with empty values
		
		ArrayList<List<String>> table = new ArrayList<List<String>>();
		
		for(int i =0; i<this.V.size(); i++) {
			
			List<String> temp = new ArrayList<String>();
			
			for(int j=0; j<=this.T.size(); j++) {
				
				temp.add("empty");
			}
			
			
			table.add(temp);
			
		}
		
		
		for(int v =0; v<this.V.size(); v++) {
			

						
			//Here for each variable we save the follow set
			List<String> current_variable_follows = new ArrayList<String>();
			String follows_string = this.follows.get(v);
			
			for(int f =0; f<follows_string.length(); f++) {
				
				String t = follows_string.charAt(f) + "";
				current_variable_follows.add(t);
				
			}
			

			
			
			
		//Here we are passing on each rule in the Variable LHS
			

			
			
			for(int r=0; r<this.R.get(v).size(); r++) {
				
				
				String current_rule = this.R.get(v).get(r);
				
				//Here for every rule we save its first set
				
				List<String> current_rule_firsts = new ArrayList<String>();
				
				String firsts_string = this.firsts.get(v).get(r);
				
				for(int f=0; f<firsts_string.length(); f++) {
					
					String t = firsts_string.charAt(f) + "";
					current_rule_firsts.add(t);
					
					
				}
				
				
				
				

				
				//Here we check the firsts of the rule and act accordingly
				
				for(int m =0; m<current_rule_firsts.size(); m++) {
					
					
					String current_first = current_rule_firsts.get(m);
					
					if(! current_first.equals("e")) {
						
						
						int current_first_index = this.T.indexOf(current_first);
						
						table.get(v).set(current_first_index, current_rule);
						
						
						
					}
					
					
				//Here we check if the rule has epsilon in its first and act accordingly
				
					if(current_rule_firsts.contains("e")) {
						
						
						for(int mm =0 ; mm<current_variable_follows.size(); mm++) {
							
							
							String current_follow = current_variable_follows.get(mm);
							
							if(current_follow.equals("$")) {
								
								table.get(v).set(this.T.size(), current_rule);
								
								
							}
							
							else {
								
								int current_follow_index = this.T.indexOf(current_follow);
								table.get(v).set(current_follow_index, current_rule);
								
							}
							
							
						}
						
						
						
					}
					
			 
					
					
					
					
					
					
					
					
				}
				
				
				
				
				
				
		
				
				
			}
		
			
			
			
		}
		
		
		
		
		
		
		
		this.predictive_parsing_table = table;
		
	}
	
	
	public String parse(String input) {
		// TODO Auto-generated method stub
		
		
		//We first call the method to create the predictive parsing table of the CFG
		create_predictive_parsing_table();
		
		
		
		List<String> result = new ArrayList<String>();
		
		Stack <String> pda = new Stack<String>();
		
		//Here we push $ and Start Variable at the beginning
		pda.push("$");
		pda.push("S");
		
		result.add("S");
		int pointer = 0;
		String tape = input + "$";
		int loop = 2;
		
		
		while(loop==2) {
			
			String top = pda.peek();
			
			
			//First we get the symbol where the pointer at
			String current = tape.charAt(pointer)+"";
			
			//We check if both input and top of stack is $ then we exit
			
			if(top.equals("$") && current.equals("$")) {
				break;
				
			}
			
			
			//We check if the top of the stack is a terminal then we act accordingly
			
			if(this.T.contains(top) || top.equals("$")) {
				
				if(!current.equals(top)) {
					
					result.add("ERROR");
					break;
				}
				else {
					pda.pop();
					pointer++;
					
				}
				
			}
			
			
			//Otherwise the top of the stack is a Variable
			
			else {
				

							
				int variable_index = this.V.indexOf(top);
			
				int terminal_index;
				
				if(current.equals("$")) {
					
					terminal_index = this.T.size();
				}
				else {
					terminal_index = this.T.indexOf(current);
				}
				
				
				//Here we check if the corresponding entry in the predictive parsing table is empty then we return an error , otherwise proceed
				
				String entry = predictive_parsing_table.get(variable_index).get(terminal_index);
				
				if(entry.equals("empty")) {
					
					result.add("ERROR");
					break;
					
				}
				
				else {
					
					//Now we update the PDA
					
					String popped_variable = pda.pop();
					
					if(!entry.equals("e")) {
					
					for(int h =entry.length()-1; h>=0; h--) {
						
						String tmp = entry.charAt(h) + "";
						pda.push(tmp);
						
						
					}
				}
					
					
				//Now we update the derivation
					
					
				//Here we check if we did push epsilon , meaning we just need to remove the variable we popped and push nothing	
				if(entry.equals("e")) {
					
					String last_derivation = result.get(result.size()-1);
					String new_derivation;
					
					for(int ii=0; ii<last_derivation.length(); ii++) {
						
						String check = last_derivation.charAt(ii)+"";
						if(popped_variable.equals(check)) {
							
							if(ii ==0) {
								
								new_derivation = last_derivation.substring(1);
								result.add(new_derivation);
								break;
								
							}
							
							else {
								if(ii ==last_derivation.length()-1) {
								
								new_derivation = last_derivation.substring(0,ii);
								result.add(new_derivation);
								break;
									
									
								}
								
								else {
									
								new_derivation = last_derivation.substring(0,ii) + last_derivation.substring(ii+1);
								result.add(new_derivation);
								break;
									
									
								}
								
								
							}
							
							
						}
						
					}
					
					
					
					
				}
				
				
				//Otherwise we need to replace the variable with the entry/rule
				else {
					
					
					String last_derivation = result.get(result.size()-1);
					String new_derivation;
					
					for(int ii=0; ii<last_derivation.length(); ii++) {
						
						String check = last_derivation.charAt(ii)+"";
						if(popped_variable.equals(check)) {
							
							if(ii ==0) {
								
								new_derivation = entry + last_derivation.substring(1);
								result.add(new_derivation);
								break;
								
							}
							
							else {
								if(ii ==last_derivation.length()-1) {
								
								new_derivation = last_derivation.substring(0,ii) + entry;
								result.add(new_derivation);
								break;
									
									
								}
								
								else {
									
								new_derivation = last_derivation.substring(0,ii) + entry + last_derivation.substring(ii+1);
								result.add(new_derivation);
								break;
									
									
								}
								
								
							}
							
							
						}
						
					}
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				}
					
					
				
					
					
					
					
					
					
				}
				
				
				
				
				
			}
			
			
		}
		
		
		
		
		
		
		
		String final_result = String.join(";", result);
		
		
		
		return final_result;
	}
	
	
	
	
	

}
