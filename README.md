#Really a Markup Language
by Kingroka

###About
Really a Markup Language (or RML) is an XML based scripting language coded in Java. RML's syntax is designed to be an effective and custimizable language while retaining the simplicity of XML. 

Withtin this repository is the early workings of the java library that will parse and excecute RML code while also being flexible to custom RML syntax. 

###Example Code
```
  <rml class="Main">
	<main>
	//Defining Variables
		<int name="index" value="500"/int>
		<string name="line" value="This is a test of strings"/string>
		//Print Function

		<@func name="test" /@func>
	</main>
	<func name="test">
		<if cond="true">
			<@var name="index" set="80"/@var>
		</if>
		<print>
			<@var name="index"/@var>
		</print>
	</func>
	
</rml>
```
###*Keep in Mind*
This project is in its very early stages of development and is subject to drastic changes in the future. That being said, feedback is very valuable and is much appreciated.
