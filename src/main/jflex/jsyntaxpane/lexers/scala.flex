/*
 * Copyright 2008 Ayman Al-Sairafi ayman.alsairafi@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License
 *       at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * --
 *
 * The original .flex file is full of errors. With my inexisting JFlex
 * knowledge, I try to fix the problems based on the Scala Language
 * Specification (SLS) v2.9 (published draft May 24, 2011). (H. H. Rutz)
 */

package jsyntaxpane.lexers;

import jsyntaxpane.Token;
import jsyntaxpane.TokenType;

%%

%public
%class ScalaLexer
%extends DefaultJFlexLexer
%final
%unicode
%char
%type Token


%{
    /**
     * Create an empty lexer, yyrset will be called later to reset and assign
     * the reader
     */
    public ScalaLexer() {
        super();
    }

    @Override
    public long yychar() {
        return yychar;
    }

    private static final byte PARAN     = 1;
    private static final byte BRACKET   = 2;
    private static final byte CURLY     = 3;

%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]+

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment}

/* plain comment added by HHR as a quick work-around for embedding non-highlighted text portions */
PlainComment = "/*---" [^*] ~"*/"
TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

HexIntegerLiteral = 0 [xX] 0* {HexDigit} {1,8}
HexLongLiteral    = 0 [xX] 0* {HexDigit} {1,16} [lL]
HexDigit          = [0-9a-fA-F]

OctIntegerLiteral = 0+ [1-3]? {OctDigit} {1,15}
OctLongLiteral    = 0+ 1? {OctDigit} {1,21} [lL]
OctDigit          = [0-7]

/* floating point literals */
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]*
FLit2    = \. [0-9]+
FLit3    = [0-9]+
Exponent = [eE] [+-]? [0-9]+

/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

%state STRING, CHARLITERAL, JDOC, JDOC_TAG

%%

<YYINITIAL> {

  /* keywords, SLS 1.1 */
  "abstract"       |
  "case"           |
  "catch"          |
  "class"          |
  "def"            |
  "do"             |
  "else"           |
  "extends"        |
  "false"          |
  "final"          |
  "finally"        |
  "for"            |
  "forSome"        |
  "if"             |
  "implicit"       |
  "import"         |
  "lazy"           |
  "macro"          |
  "match"          |
  "new"            |
  "null"           |
  "object"         |
  "override"       |
  "package"        |
  "private"        |
  "protected"      |
  "return"         |
  "sealed"         |
  "super"          |
  "this"           |
  "throw"          |
  "trait"          |
  "try"            |
  "true"           |
  "type"           |
  "val"            |
  "var"            |
  "while"          |
  "with"           |
  "yield"          { return token(TokenType.KEYWORD); }

/* "_" ":" "=" "=>" "<-" "<:" "<%" ">:" "#" "@" */

  /* Java Built in types and wrappers XXX Wrong -- doesn't make sense to add a list */
  "Unit"                         |
  "Boolean"                      |
  "Byte"                         |
  "Char"                         |
  "Short"                        |
  "Int"                          |
  "Long"                         |
  "Float"                        |
  "Double"                       |
  "Any"                          |
  "AnyRef"                       |
  "String"                       { return token(TokenType.TYPE); }

  /* Some Scala predefines */
/*  "println"                      { return token(TokenType.KEYWORD2); } */

  /* Some Java standard Library Types */
/*  "Throwable"                    |
  "Cloneable"                    |
  "Comparable"                   |
  "Serializable"                 |
  "Runnable"                     { return token(TokenType.TYPE); }
*/
  "WARNING"                      { return token(TokenType.WARNING); }
  "ERROR"                        { return token(TokenType.ERROR); }

  /* operators */

  "("                            { return token(TokenType.OPERATOR,  PARAN); }
  ")"                            { return token(TokenType.OPERATOR, -PARAN); }
  "{"                            { return token(TokenType.OPERATOR,  CURLY); }
  "}"                            { return token(TokenType.OPERATOR, -CURLY); }
  "["                            { return token(TokenType.OPERATOR,  BRACKET); }
  "]"                            { return token(TokenType.OPERATOR, -BRACKET); }
  ";"                            |
  ","                            |
  "."                            |
  "="                            |
  ">"                            |
  "<"                            |
  "!"                            |
  "~"                            |
  "?"                            |
  ":"                            |
  "=="                           |
  "<="                           |
  ">="                           |
  "!="                           |
  "&&"                           |
  "||"                           |
  "++"                           |
  "--"                           |
  "+"                            |
  "-"                            |
  "*"                            |
  "/"                            |
  "&"                            |
  "|"                            |
  "^"                            |
  "%"                            |
  "<<"                           |
  ">>"                           |
  ">>>"                          |
  "+="                           |
  "-="                           |
  "*="                           |
  "/="                           |
  "&="                           |
  "|="                           |
  "^="                           |
  "%="                           |
  "<<="                          |
  ">>="                          |
  ">>>="                         { return token(TokenType.OPERATOR); }

  /* string literal */
  \"                             {
                                    yybegin(STRING);
                                    tokenStart = (int) yychar;
                                    tokenLength = 1;
                                 }

  /* character literal */
  \'                             {
                                    yybegin(CHARLITERAL);
                                    tokenStart = (int) yychar;
                                    tokenLength = 1;
                                 }

  /* numeric literals */

  {DecIntegerLiteral}            |
  {DecLongLiteral}               |

  {HexIntegerLiteral}            |
  {HexLongLiteral}               |

  {OctIntegerLiteral}            |
  {OctLongLiteral}               |

  {FloatLiteral}                 |
  {DoubleLiteral}                |
  {DoubleLiteral}[dD]            { return token(TokenType.NUMBER); }

  // JavaDoc comments need a state so that we can highlight the @ controls
  "/**"                          {
                                    yybegin(JDOC);
                                    tokenStart = (int) yychar;
                                    tokenLength = 3;
                                 }

  /* comments */
  {PlainComment}                 { return token(TokenType.DEFAULT); }
  {Comment}                      { return token(TokenType.COMMENT); }

  /* whitespace */
  {WhiteSpace}                   { }

  /* identifiers */
  {Identifier}                   { return token(TokenType.IDENTIFIER); }
}


<STRING> {
  \"                             {
                                     yybegin(YYINITIAL);
                                     // length also includes the trailing quote
                                     return token(TokenType.STRING, tokenStart, tokenLength + 1);
                                 }

  {StringCharacter}+             { tokenLength += yylength(); }

  \\[0-3]?{OctDigit}?{OctDigit}  { tokenLength += yylength(); }

  /* escape sequences */

  \\.                            { tokenLength += 2; }
  {LineTerminator}               { yybegin(YYINITIAL);  }
}

<CHARLITERAL> {
  \'                             {
                                     yybegin(YYINITIAL);
                                     // length also includes the trailing quote
                                     return token(TokenType.STRING, tokenStart, tokenLength + 1);
                                 }

  {SingleCharacter}+             { tokenLength += yylength(); }

  /* escape sequences */

  \\.                            { tokenLength += 2; }
  {LineTerminator}               { yybegin(YYINITIAL);  }
}

<JDOC> {
  "*/"                           {
                                     yybegin(YYINITIAL);
                                     return token(TokenType.COMMENT, tokenStart, tokenLength + 2);
                                 }

  "@"                            {
                                     yybegin(JDOC_TAG);
                                     int start = tokenStart;
                                     tokenStart = (int) yychar;
                                     int len = tokenLength;
                                     tokenLength = 1;
                                     return token(TokenType.COMMENT, start, len);
                                 }

  .|\n                           { tokenLength ++; }

}

<JDOC_TAG> {
  ([:letter:])+ ":"?             { tokenLength += yylength(); }

  "*/"                           {
                                     yybegin(YYINITIAL);
                                     return token(TokenType.COMMENT, tokenStart, tokenLength + 2);
                                 }

  .|\n                           {
                                     yybegin(JDOC);
                                     // length also includes the trailing quote
                                     int start = tokenStart;
                                     tokenStart = (int) yychar;
                                     int len = tokenLength;
                                     tokenLength = 1;
                                     return token(TokenType.COMMENT2, start, len);
                                 }
}


/* error fallback */
.|\n                             {  }
<<EOF>>                          { return null; }

