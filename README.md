Really a Markup Language
======

##About
Really a Markup Language (or RML) is an XML based scripting language coded in Java. RML's syntax is designed to be an effective and customizable language while retaining the readability of XML. 

Within this repository is the early workings of the java library that will parse and execute RML code while also being flexible to custom RML syntax. 

##Example Code
```
<rml class="Main">
//Functions with a '$' in front of their name will be executed on runtime
	<func name="$main">
		<double name="index" value="500"/>
		<string name="line" value="Condition is true"/>
		<boolean name="bool" value="false"/>	
		<@var name="bool" set="true" />	
		<@func name="test"/>
	</func>
	
	<func name="test">	
		<if cond="bool">
			<print>
				<@var name="line"/>
			</print>
		</if>
	</func>
</rml>

```


###*Keep in Mind*
This project is in its very early stages of development and is subject to drastic changes in the future. That being said, feedback is very valuable and is much appreciated.
