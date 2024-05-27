package csen1002.main.task4;

import java.util.*;
/**
 * Write your info here
 * 
 * @name Yousef Mohamed Hassan Mohamed
 * @id 49-0560
 * @labNumber 20
 */

public class CfgEpsUnitElim {
	
	
	List<String> V;
	List<String> T;
	List<String> R;

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *             representation follows the one in the task description
	 */
	public CfgEpsUnitElim(String cfg) {
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
		
		

		
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String variables_tmp = String.join(";", this.V);
		String terminals_tmp = String.join(";", this.T);
		String rules_tmp = String.join(";", this.R);
		
		String result = variables_tmp + "#" + terminals_tmp + "#" + rules_tmp;
		
		
		
		
		return result;
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {
		// TODO Auto-generated method stub
		
		
		ArrayList<String> rules_cut = new ArrayList<String>();
		ArrayList<List<String>> rules_each = new ArrayList<List<String>>();
		ArrayList<List<String>> rules_each_copy = new ArrayList<List<String>>();
		
		
		
		
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
		
		for(int j=0; j<rules_each.size(); j++) {
			
			rules_each_copy.add(rules_each.get(j));
			
		}
		
		
		
		
		boolean Epsilon_found = true;
		List<Integer> dealt_with = new ArrayList<Integer>();
		
		while(Epsilon_found){

			
			Epsilon_found = false;
			
			for(int i=0; i<rules_each_copy.size(); i++) {
				
				
				
				if(rules_each_copy.get(i).contains("e")) {

					
					Epsilon_found = true;
					dealt_with.add(i);
					int epsilon_index = rules_each_copy.get(i).indexOf("e");
					
					

					
				
					rules_each_copy.get(i).remove(epsilon_index);
					
					
					
					int current_variable_index = i;
					String current_variable = this.V.get(current_variable_index);
					
					
					//Now loop over each production to replace the e
					for(int m =0 ; m<rules_each_copy.size(); m++) {
						
						
						
						
						
						for(int mm=0; mm<rules_each_copy.get(m).size(); mm++) {
							
							String current_production = rules_each_copy.get(m).get(mm);
							List<Integer> positions = new ArrayList<Integer>();
							

							
							for(int c =0; c<current_production.length(); c++) {
								
								if((current_production.charAt(c)+"").equals(current_variable)) {
									
									positions.add(c);
								}
								
							}

							
							
							List<List<Integer>> res = new ArrayList<List<Integer>>();
						    List<Integer> dd = new ArrayList<Integer>(positions.size());
						    for(int ii=0; ii<positions.size(); ii++) {
						    	
						    	dd.add(0);
						    	
						    }
						    
						    for(int r=0; r<= positions.size(); r++) {
						    	
						    	
						    	different_combinations(positions,dd,0,positions.size()-1,0,r,res);
						    	
						    }
						    
						    
						    for(int t =0; t<res.size(); t++) {
						    	
						    	String current_production1 = rules_each_copy.get(m).get(mm);
						    	
						    	if(!((current_production1 + "").equals(current_variable))){
						    	
						    		
						    	
						    	
						    		
						    		String tmp22 = "";
						    		
						    		
						    		
						    		
						    		
						    		
						    		for(int hh=0; hh<current_production1.length(); hh++) {
						    			
						    			if(!(res.get(t).contains(hh))) {
						    				
						    				tmp22 = tmp22 + current_production1.charAt(hh);
						    				

						    				
						    				
						    			}
						    		}
						    		
						    		
						    		
						    		current_production1 = tmp22;
						    	
						    	
						    	}
						    	
						    	else {
						    		
						    		
						    		current_production1 = "e";
						    		
						    		
						    		
						    	}
						    	
						    	//here we will add the new production 
						    	if(!rules_each.get(m).contains(current_production1)) {
						    		
						    		if(! (current_production1.equals("e") && dealt_with.contains(m)) ) {
						    		
						    			if(!(current_production1.equals(""))) {
						    					rules_each.get(m).add(current_production1);
						    					
						    		
						    			}
						    		}
						    		
						    		
						    	}
						    	
						    }
						    
						    
						    
						}
						    
						   
						    
						    
						   
							
							
							
							
					
							
							
							
							
							
							
							
							
						
						
						
						
				
				}
					
					
					
					
					
					
					
				}
				
				

				
				
				
				
				
				
			}
			
			

			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		//sort rules_each List and assign it to its corresponding static variable
		
		
		for(int c=0; c<rules_each.size(); c++) {
		
			
			Collections.sort(rules_each.get(c));
			
		}

		
		
		for(int c=0 ; c<rules_each.size(); c++) {
			
			String current_v = this.V.get(c);
			this.R.set(c, current_v + "/" +( String.join(",",rules_each.get(c))));
			
			
			
		}

		
		
		

		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {
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
		
		
		
		
		
		boolean isChange = true;
		
		while(isChange) {
			
			isChange = false;
		
		for(int i=0; i<this.V.size(); i++) {
			
			
			for(int ii=0; ii<rules_each.size(); ii++) {
				
				
				if(rules_each.get(ii).contains(this.V.get(i))) {
					
					isChange = true;
					
					String current_var = this.V.get(i);
					int index_to_be_removed = rules_each.get(ii).indexOf(current_var);
					rules_each.get(ii).remove(index_to_be_removed);
					
					
					List<String> rules_to_be_added = rules_each.get(i);
					
					for(int j=0; j<rules_to_be_added.size(); j++) {
						
						String tmp = rules_to_be_added.get(j);
						
						if(!(rules_each.get(ii).contains(tmp))) {
							
							rules_each.get(ii).add(tmp);
							
							
						}
						
						
					}
					
					
				}
				
				
				
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
		}
		}
		
		for(int i=0; i<rules_each.size(); i++) {
			
			Collections.sort(rules_each.get(i));
			
		}
		
		
		for(int c=0 ; c<rules_each.size(); c++) {
			
			String current_v = this.V.get(c);
			this.R.set(c, current_v + "/" +( String.join(",",rules_each.get(c))));
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	

	
static void different_combinations(List<Integer> l, List<Integer>tmp, int first, int last, int pointer, int capacity,List<List<Integer>> res) {
	
	
		
    while (true) {
        if (pointer == capacity) {
            int j = 0;
            List<Integer> store = new ArrayList<Integer>();
            while (j < capacity) {
                store.add(tmp.get(j));
                j++;
            }
            if(store.isEmpty() == false) {
            	
            	res.add(store);
            }

            return;
        }

        int i = first;
        while (i <= last && last - i + 1 >= capacity - pointer) {
            tmp.set(pointer, l.get(i));
            different_combinations(l, tmp, i + 1, last, pointer + 1, capacity,res);
            i++;
        }
        return;
    }

			
}
	
	}

