/**
 * Write your info here
 *
 * @name Yousef Mohamed Hassan Mohamed
 * @id 49-0560
 * @labNumber 20
 */

grammar Task7;

/**
 * This rule is to check your grammar using "ANTLR Preview"
 */
test: (ZERO | ONE | ERROR )+  EOF; //Replace the non-fragment lexer rules here

WS: [ \r\t\n]+ -> skip;
ZERO: (Segment1 | Segment2 | Segment3 );
ONE: (Segment4 | AllZero);
ERROR: Short;
// Write all the necessary lexer rules and fragment lexer rules here
fragment Segment1: Binary Zero One;
fragment Segment2: Binary One Zero;
fragment Segment3: One Zero Zero;
fragment Segment4: Binary One One;
fragment Short: Binary? Binary;
fragment AllZero: Zero Zero Zero;
fragment Binary: One | Zero;
fragment One : [1];
fragment Zero: [0];
