package csen1002.main.task6;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Yousef Mohamed Hassan Mohamed
 * @id 49-0560
 * @labNumber 20
 */

public class CfgFirstFollow {
	
	
	
	List<String> V;
	List<String> T;
	List<String> R;
	ArrayList<List<String>> first;

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgFirstFollow(String cfg) {
		// TODO Auto-generated constructor stub
		
		
		String [] tmp = cfg.split("#");
		
		String [] variables0 = tmp[0].split(";");
		String [] terminals0 = tmp[1].split(";");
		String [] rules0 = tmp[2].split(";");
		
		ArrayList<String> variables = new ArrayList<String>();
		ArrayList<String> terminals = new ArrayList<String>();
		ArrayList<String> rules = new ArrayList<String>();
		
		
		
		for(int i =0; i<variables0.length; i++) {
			
			variables.add(variables0[i]);
			
		}
		
		for(int i =0; i<terminals0.length; i++) {
			
			terminals.add(terminals0[i]);
			
		}
		
		for(int i =0; i<rules0.length; i++) {
			
			rules.add(rules0[i]);
			
		}
		
		
		
		this.V = variables;
		this.T = terminals;
		this.R = rules;
		
		first = new ArrayList<List<String>>();
		
		for(int i=0; i<this.V.size(); i++) {
			
			ArrayList<String> x = new ArrayList<String>();
			first.add(x);
			
		}
		
		
		

		
		
		
		
		
	}

	/**
	 * Calculates the First Set of each variable in the CFG.
	 * 
	 * @return A string representation of the First of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String first() {
		// TODO Auto-generated method stub
		
		
		
		ArrayList<String> rules_cut = new ArrayList<String>();
		ArrayList<List<String>> rules_each = new ArrayList<List<String>>();

		
		for(int i=0; i<this.R.size(); i++) {
			
			
			rules_cut.add(this.R.get(i).substring(2,this.R.get(i).length()));
			
			
			
				}
		
		
		
		for(int j=0 ; j<rules_cut.size(); j++) {
			
			
			String [] tmp = rules_cut.get(j).split(",");
			List<String> tmp1 = new ArrayList<String>();
			
			for(int jj =0 ; jj<tmp.length; jj++) {
				
				tmp1.add(tmp[jj]);
				
				
			}
			
			
			
			rules_each.add(tmp1);
			
			
			
		}
		
		
		
		//Start of FIRST Algorithm (looping over each Variable and its productions)
		
		
		boolean isChange = true;
		
		while(isChange) {
		
		
		isChange = false;
			
		for(int i=0; i<this.V.size(); i++) {
			
			
			List<String> current_rules = rules_each.get(i);
			

			
			
			
			for(int j=0; j<current_rules.size(); j++) {
				
				String current_production = current_rules.get(j);
				
					
				
				//Checking if it starts with a terminal
				
					String current_production_start = current_production.charAt(0) + "";
					
					if(!(this.V.contains(current_production_start))) {
						
						if(! (first.get(i).contains(current_production_start))) {
						
						first.get(i).add(current_production_start);
						isChange = true;}
						
					}
					
					//Otherwise it starts with a Variable
					else {
						
						for(int x =0; x<current_production.length(); x++) {
							
							String current_symbol = current_production.charAt(x) +"";
							
							if(!(this.V.contains(current_symbol))) {
								
								if(! (first.get(i).contains(current_symbol))) {
								
								first.get(i).add(current_symbol);
								isChange = true;
								}
								
								break;
								
							}
							
							
					else {
							
		
						int variable_start_index = this.V.indexOf(current_symbol);
						
						if(!(first.get(variable_start_index).isEmpty())) {
							
							
							for(int k=0; k<first.get(variable_start_index).size(); k++) {
								
								if( ! (first.get(i).contains(first.get(variable_start_index).get(k)))) {
									
									
									if(x == current_production.length()-1) {
									first.get(i).add(first.get(variable_start_index).get(k));
									isChange = true;
									}
									
									
									else {
										
										if(!(first.get(variable_start_index).get(k)).equals("e")) {
											
											first.get(i).add(first.get(variable_start_index).get(k));
											isChange = true;
											
										}
										
										
									}
									
								}
								
								
							}
							
							
						if( !(first.get(variable_start_index).contains("e") )  && !(rules_each.get(variable_start_index).contains("e")) ) {	
							
							break;
							
						    }
						}
						
						else {
							
							break;
						}
						
						
					
						
						
				
							
							
						}
						
						
						
						
						
						
						
		
						
						

						
						
						
						
						
						 
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					}
					
				
				
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
			
		
		}
		
		

		
		
		
		
		
		
		}
		
		
		}
		
		
		for(int h=0; h<first.size(); h++) {
			
			Collections.sort(first.get(h));
			
		}
		

		
		
		ArrayList<String> result0 = new ArrayList<String>();
		
		for(int h=0; h<first.size(); h++) {
			
			result0.add(this.V.get(h) + "/" + String.join("", first.get(h)));
			
		}
		

		
		String result = String.join(";", result0);

		
		
		
		return result;
		}

	

	/**
	 * Calculates the Follow Set of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		// TODO Auto-generated method stub
		
		ArrayList<List<String>> follow = new ArrayList<List<String>>();
		
		for(int i=0; i<this.V.size(); i++) {
			
			ArrayList<String> x = new ArrayList<String>();
			follow.add(x);
			
		}
		
		first();
		
		ArrayList<String> rules_cut = new ArrayList<String>();
		ArrayList<List<String>> rules_each = new ArrayList<List<String>>();

		
		for(int i=0; i<this.R.size(); i++) {
			
			
			rules_cut.add(this.R.get(i).substring(2,this.R.get(i).length()));
			
			
			
				}
		
		
		
		for(int j=0 ; j<rules_cut.size(); j++) {
			
			
			String [] tmp = rules_cut.get(j).split(",");
			List<String> tmp1 = new ArrayList<String>();
			
			for(int jj =0 ; jj<tmp.length; jj++) {
				
				tmp1.add(tmp[jj]);
				
				
			}
			
			
			
			rules_each.add(tmp1);
			
			
			
		}
		
		
		//start of FOLLOW Algorithm
		
		
		boolean isChange = true;
		
		while(isChange) {
		
		
			isChange = false;
			
			
			
			for(int i =0; i<this.V.size(); i++) {
				
				String current_variable = this.V.get(i);
				
				if(current_variable.equals("S")) {
					
					if( ! (follow.get(i).contains("$"))) {
					
						follow.get(i).add("$");
						isChange = true;
					}
					
				}
				
				//Now we get each production then move on each symbol in it
				
				for(int x =0; x<rules_each.size(); x++) {
					
					List<String> table = rules_each.get(x);
					
					for(int xx=0 ; xx<table.size(); xx++) {
						
						String current_production = table.get(xx);
						
						
						
						//now we check on the production if it has the variable and get its position 
						boolean found = false;
						int start_position = 0;
						
						
			for(int ss=0; ss<current_production.length(); ss++) {
							
							
					String current_symbol0 = current_production.charAt(ss) + "";
					if(current_symbol0.equals(current_variable)) {
						found =true;
						start_position = ss;
								
								
							}
							
						
						
						
			//Here we check if we found the variable in the current production or not
					
			if(found) {
				
				
				//First we check if our current variable is at the end of the production , we immediately add the follow
				if(start_position == current_production.length()-1) {
					
					
					
					if( !(follow.get(x).isEmpty())) {
						
						List<String> follow_to_add = follow.get(x);
						
						for(int n =0; n<follow_to_add.size(); n++) {
							
							if( ! (follow.get(i).contains(follow_to_add.get(n)))) {
								
								
								follow.get(i).add(follow_to_add.get(n));
								isChange=true;
								
								
							}
							
							
						}
						
						
						
						
						
					}
					
					
					
					
				}
				
				
				
				
				
				
				
				
				
			else {
						
					for(int s=start_position+1; s<current_production.length(); s++) {
							
							
							String current_symbol = current_production.charAt(s) + "";
						
								
								//we first check if the variable is not at the end of the production and act accordingly
								
								if(s<current_production.length()-1) {
									
									//String next_symbol = current_production.charAt(s) +"";
									
									
									//here we check if the next symbol is a terminal then add it immediately and break
									
									if(!(this.V.contains(current_symbol))) {
										
										if(! (follow.get(i).contains(current_symbol))) {
										
										follow.get(i).add(current_symbol);
										isChange = true;
										
										
										
										}
										
										break;
										
									}
									
									
									
									//here is the latter case, means that the next symbol is a variable 
									
									else {
										
										int next_symbol_index = this.V.indexOf(current_symbol);
										
										List<String> table_to_be_added = first.get(next_symbol_index);
										
										//loop over the table and add each element but remove e
										
										for(int t=0; t<table_to_be_added.size(); t++) {
											
											String element_to_be_added = table_to_be_added.get(t);
											
											if(!(element_to_be_added.equals("e"))) {
												
												if(! (follow.get(i).contains(element_to_be_added))) {
													
													follow.get(i).add(element_to_be_added);
													isChange = true;
													
													}

											}
											
										}
										
										
										
										//here we check if the variable has epsilon in its first or not and act accordingly
										
										if( !(first.get(next_symbol_index).contains("e")) ) {
											
											
											break;
											
										}
										
										//if it has epsilon in its first we will continue the for loop normally
										
										
										
										
										
										
										
									}
									
									
									
									
									
									
									
									
									
									
									
									
								}
								
						else {
									
							//Here we are at the case of last symbol of the production
							
							
							
							//Either the last symbol is a terminal , so we add it immediately

							if(!(this.V.contains(current_symbol))) {
									
									if(! (follow.get(i).contains(current_symbol))) {
									
									follow.get(i).add(current_symbol);
									isChange = true;
									
									
									}
									break;
									
								}
								
								
								
								
								
							
							
							
							//Or either the last symbol is our variable itself , so we automatically add the follow of the variable leading to this production
						else {
							if(current_symbol.equals(current_variable)) {
								
								if( !(follow.get(x).isEmpty())) {
									
									List<String> follow_to_add = follow.get(x);
									
									for(int n =0; n<follow_to_add.size(); n++) {
										
										if( ! (follow.get(i).contains(follow_to_add.get(n)))) {
											
											
											follow.get(i).add(follow_to_add.get(n));
											isChange=true;
											
											
										}
										
										
									}
									
									
									
									
									
								}
								
								
								
								
								
								
							}
							
							
							//here we are in the case of otherwise, means that this last symbol is a different variable 
							
							
							//we add the first of this variable to our current follow
							
							
							int next_symbol_index = this.V.indexOf(current_symbol);
							
							List<String> table_to_be_added = first.get(next_symbol_index);
							
							//loop over the table and add each element but remove e
							
							for(int t=0; t<table_to_be_added.size(); t++) {
								
								String element_to_be_added = table_to_be_added.get(t);
								
								if(!(element_to_be_added.equals("e"))) {
									
									if(! (follow.get(i).contains(element_to_be_added))) {
										
										follow.get(i).add(element_to_be_added);
										isChange = true;
										
										}

								}
								
							}
							
							
							//now we check if this variable has epsilon , we add the follow of the variable leading to the current production
							
							if(first.get(next_symbol_index).contains("e")) {
								
								
								if( !(follow.get(x).isEmpty())) {
									
									List<String> follow_to_add = follow.get(x);
									
									for(int n =0; n<follow_to_add.size(); n++) {
										
										if( ! (follow.get(i).contains(follow_to_add.get(n)))) {
											
											
											follow.get(i).add(follow_to_add.get(n));
											isChange=true;
											
											
										}
										
										
									}
									
									
									
									
									
								}
								
								
								
								
								
								
							}
							
							
							
							
							
					
									
						}
									
									
									
									
									
									
									
									
									
									
								}
								
								
								
								
								
								
								
								
								
								
							
							
							
							
							
							
							
							
							
						}}
					}
					}
			
						
						
						
						
						
						
						
						
					}
					
					
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
		
		
		for(int h=0; h<follow.size(); h++) {
			
			Collections.sort(follow.get(h));
			
		}
		
		
		ArrayList<String> result0 = new ArrayList<String>();
		
		for(int h=0; h<follow.size(); h++) {
			
			result0.add(this.V.get(h) + "/" + String.join("", follow.get(h)));
			
		}
		

		
		String result = String.join(";", result0);

		
		
		
		return result;

	}
	
	
	
	
	
	
	
	


}
