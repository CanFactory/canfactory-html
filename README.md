Can Factory Html
================

Classes for working with HTML, in particular to make it easier to test and verify HTML in unit tests, which was
the original purpose.

The library essentially provides a more fluent style interface over the [JSOUP html parser](http://jsoup.org/) and
also integrates nicely with [Hamcrest](http://hamcrest.org/JavaHamcrest/), which makes tests easier to write.


Requires
--------
* Java 7
* Apache Maven


From Maven
----------
```xml
<repositories>
		<repository>
			<id>canfactory</id>
			<url>http://stage.canfactory.com/artifactory/libs-release</url>
		</repository>
</repositories>


<dependency>
    <groupId>com.canfactory</groupId>
    <artifactId>canfactory-html</artifactId>
    <version>0.1.0</version>
</dependency>

```


To build and install
---------------------
mvn clean install


Stability
---------
This library is used to support unit tests in production code and can be considered relatively stable.
New features subject to change are marked with @Beta annotation

Usage
-----

###  HtmlElement

An HTML element has a single root node but can have any number of children, for example:

```html
<ul>
    <li>Red</li>
    <li>Green</li>
    <li>Blue</li>
</ul>
```

#### Create from a Stream

```java
HtmlElement.Factory.fromStream(InputStream stream)
```

#### Create from a String

```java
HtmlElement.Factory.fromStream(String html)
```

### Create from a JSoup Element

```java
HtmlElement.Factory.fromStream(Element element)
```

###  HtmlFragment

An HTML fragment is a block of HTML that has no single root node, for example:

```html
<p>This is paragraph 1</p>
<p>This is paragraph 2</p>
<ul>
    <li>Red</li>
    <li>Green</li>
    <li>Blue</li>
</ul>
```

#### Create from an Input Stream

```java
HtmlFragment.Factory.fromStream(InputStream stream)
```

#### Create from a String

```java
HtmlFragment.Factory.fromString(String html)
```

#### Create from JSoup Elements

```java
HtmlFragment.Factory.fromElements(Elements elements)
```

### Finding Elements

Having either an element or a fragment you can find other elements and fragments for use in your assertions.
There are other methods available, but these are the most common used.

#### All

Find all the elements matching the given CSS selector. N.B CSS selectors use the [JSOUP syntax](http://jsoup.org/cookbook/extracting-data/selector-syntax).
This may differ slightly from the formal CSS specification.

```java
html.all(String cssSelector)
```

#### First

Find the first element matching the given CSS selector.

```java
html.first(String cssSelector)
```

#### Last

Find the last element matching the given CSS selector.

```java
html.last(String cssSelector)
```

#### Nth

Find the nth element matching the given CSS selector.

```java
html.nth(int index, String cssSelector)
```

_Nth is considered in CSS to be 1 based indexing, not zero_

### Chaining methods

The API is designed so that methods can be chained. Methods should not return null. This is managed by providing
implementations that handle the empty collection state, e.g. EmptyHtmlFragment. Its important to use the Factory
methods on HtmlElement and HtlmFactory as these will take care of creating the correct instance.

_For those who care, the API is designed to be vaguely Monadic, like the new stream (collections) API in Java 8.
Whats is a Monad?  The first lines of the [Wikipedia article](http://en.wikipedia.org/wiki/Monad_%28functional_programming%29)
sum up the behaviour: "In functional programming, a monad is a structure that represents computations defined as sequences of steps.
A type with a monad structure defines what it means to chain operations, or nest functions of that type together.
This allows the programmer to build pipelines that process data in steps, in which each action is decorated with additional
processing rules provided by the monad."_

_One benefit, to lift a phrase from a blog article is "a function thus bound can be guaranteed to be working with an
instance and not a null.". So we don't have to worry about null, which is nice.
In this API this is managed by implementations of HtmlElement and HtmlFragment that handle the empty collection state._

### Matching

The available matchers from the project are currently as follows, but they may be combined with existing ones in
hamcrest, most notably; not, allOf, anyOf. They are meant to be used in a fluid readable manner which may
become more obvious in some of the examples afterwards.

#### Any

Any of the found elements pass the result of the given matcher.

```java
any(Matcher<HtmlElement> matcher)
```

#### Count

The number of found elements equals a given value.

```java
count(int value)
```

#### Each

Each of the found elements pass the result of the given matcher.

```java
each(Matcher<HtmlElement> matcher)
```

#### HasAttribute

This is overloaded to provide the following functionality:

* The HTML element has an attribute present by name, ignoring the value
* The HTML element has an attribute that matches exactly by name and value
* The HTML element has all of the given attributes

```java
hasAttribute(String name)
hasAttribute(String name, String value)
hasAttribute(Attribute attribute)
hasAttributes(Attribute... attributes)
hasAttributes(String... nameValuePairs)
```

#### HasClass

The HTML element has the given class or classes.

```java
hasClass(String classname)
hasClasses(String... classnames)
```

#### HasId

The HTML element has the given ID.

```java
hasId(String id)
```

#### HasText

The HTML element contains the given text.

```java
hasText(String text)
```

#### None

None of the found elements pass the result of the given matcher.

```java
none(Matcher<HtmlElement> matcher)
```

####  One

Only one of the found elements pass the result of the given matcher.

```java
one(Matcher<HtmlElement> matcher)
```

#### Examples

Assert that each li tag found has the text "list item".

```java
assertThat(html.all("li"), each(hasText("list item")))
```

Assert that no li tag in found has the text "hello".

```java
assertThat(html.all("li"), none(hasText("list item")))
```

Assert that only one h1 has the text "Now Showing".

```java
assertThat(html.all("h1"), one(hasText("Now Showing")))
```

Assert that all div tag with the class "component" have an attribute "data-id".

```java
assertThat(html.all("div.component"), each(hasAttribute("data-id")))
```

Assert that there is only one form present.

```java
assertThat(html.all("form"), count(1))
```

Assert that the 2nd li tag has the class "second".

```java
assertThat(html.nth(2, "li"), hasClass("second"))
```
