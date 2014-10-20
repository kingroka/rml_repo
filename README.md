Really a Markup Language
======

##About
Really a Markup Language (or RML) is an XML based scripting language coded in Java. RML's syntax is designed to be an effective and customizable language while retaining the readability of XML. 

Within this repository is the early workings of the java library that will parse and execute RML code while also being flexible to custom RML syntax. 

##Example Code
```
<rml class="Main">
	<func name="$main">
		<list name="myList" type="double"/>
			<ar list="myList" value="1.56"/>
			<ar list="myList" value="2.22"/>
			<ar list="myList" value="3.78"/>
			<ar list="myList" value="4.52"/>
			
		<double name="index" value="0"/>
		<string name="line" value="Condition is true"/>
		<boolean name="bool" value="false"/>	
		<@var name="bool" set="true" />	
		<@func name="test"/>
	</func>
	<func name="test">
	//Recusrion!!!!
		<if cond="index < 4" >
			<print>
				<@var name="myList[index]"/>	
			</print>	
			<@var name="index" set="++"/>
			<@func name="test"/>
		</if>
	</func>
</rml>

```


###*Keep in Mind*
This project is in its very early stages of development and is subject to drastic changes in the future. That being said, feedback is very valuable and is much appreciated.
