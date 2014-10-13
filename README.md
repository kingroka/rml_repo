Really a Markup Language
======

##About
Really a Markup Language (or RML) is an XML based scripting language coded in Java. RML's syntax is designed to be an effective and customizable language while retaining the readability of XML. 

Within this repository is the early workings of the java library that will parse and execute RML code while also being flexible to custom RML syntax. 

##Example Code
```
<rml class="Main">
	<main>
		<int name="index" value="500"/int>
		<string name="line" value="This is a test of strings"/string>
		<boolean name="bool" value="false"/boolean>
		<print>
			<@var name="bool"/@var>
			<@var name="index"/@var>
		</print>
		<@func name="test" /@func>
	</main>
	<func name="test">
		<if cond="index * index == index * index">
			<@var name="index" set="80"/@var>
			<print>
				<@var name="index"/@var>
			</print>
		</if>
	</func>
	
</rml>
```


###*Keep in Mind*
This project is in its very early stages of development and is subject to drastic changes in the future. That being said, feedback is very valuable and is much appreciated.
