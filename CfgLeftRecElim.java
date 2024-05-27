package csen1002.main.task5;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Yousef Mohamed Hassan Mohamed
 * @id 49-0560
 * @labNumber 20
 */

public class CfgLeftRecElim {
	
	
	List<String> V;
	List<String> T;
	List<String> R;

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgLeftRecElim(String cfg) {
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
	 * Eliminates Left Recursion from the grammar
	 */
	public void eliminateLeftRecursion() {
		
		// TODO Auto-generated method stub
		
		List<String> handled = new ArrayList<String>();
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
		
		
		
		
		
		
		
		//Start of Left-Recursion Elimination Algorithm
		
		
		for(int i =0; i<this.R.size(); i++) {
			
			
			int current_size = rules_each.get(i).size();
			
			List<String> replace = new ArrayList<String>();
			
			boolean anyChange = true;
			
			
			while(anyChange) {
			
			anyChange = false;
				
			for(int j=0 ; j<current_size; j++) {
				
				
				String current_production = rules_each.get(i).get(j);
				String current_production_first = current_production.charAt(0) +"" ;
				
				
				
				
				
				
				
				
				if(handled.contains(current_production_first)) {
					
					
					anyChange = true;
					
					
					int matched_index = this.V.indexOf(current_production_first);
					
					for(int k =0; k<rules_each.get(matched_index).size() ; k++) {
						
						String tmp = rules_each.get(matched_index).get(k) + current_production.substring(1);
						replace.add(tmp);
						
						
						
					}
					
					
					

					
					
					
				}
				
				
				else {
					
					replace.add(current_production);
					
				}
				
				
				}
				

			
				
			
			
			
			
			
			
			rules_each.get(i).clear();
			
			for(int m =0; m<replace.size(); m++) {
				
				rules_each.get(i).add(replace.get(m));
				
			}
			
			
			
			current_size = rules_each.get(i).size();
			replace.clear();
			
			}
			
			

			
			
			
			//Now Applying Immediate Left-Recursion Elimination
			
			
			boolean found = false;
			
			
			for(int c =0; c<current_size; c++) {
				
				if((rules_each.get(i).get(c).charAt(0)+"").equals(this.V.get(i))) {
					
					found =true;
					break;
					
					
				}
				
			}
			
			
			if(found) {
				
				
				List<String> variable_dash = new ArrayList<String>();
				
				this.V.add(this.V.get(i) + "'");
				
				for(int c =0 ; c<current_size; c++) {
					
					String current = rules_each.get(i).get(c);
					
					if((current.charAt(0) + "") .equals(this.V.get(i))){
						
						String tmp1 = current.substring(1) + this.V.get(i) + "'";
						
						variable_dash.add(tmp1);
						
						
					}
					
					
					else {
						
						String tmp1 = current + this.V.get(i) + "'";
						rules_each.get(i).add(tmp1);
						
					}
					
					
					
					
				}
				variable_dash.add("e");
				
				for(int c=0; c<current_size; c++) {
					
					rules_each.get(i).remove(0);
					
				}
				
				rules_each.add(variable_dash);
				
				
				
				
				
				
				
				
				
			}
			
		
			handled.add(this.V.get(i));
			
			
			
			
			
			
			
			
		}
		
		
		
		

		
		
		this.R.clear();
		for(int c=0 ; c<rules_each.size(); c++) {
			
			String current_v = this.V.get(c);
			this.R.add(current_v + "/" +( String.join(",",rules_each.get(c))));
			
			
			
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
