# Jabberpoint Project
Jabberpoint is a slide show presentation tool developed for the master course Design for Change at the Open University. The project is meant to demonstrate the process of creating software in a future-proof way, by using an agreed upon ubiquitous language, applying common Object-Oriented design patterns, and following several design principles.

## Ubiquitous language
The analysis of the domain yielded a set of entities, actions and rules, that can be seen as the dictionary and the grammar of the domain. Through the use of this language, it is easier to reason about certain aspects of the domain, which will contribute to the design of insightful models and their placement in the architecture. 

### Entities
The following table depicts the entities, their meaning with regard to the domain, possible sub concepts related to an entity, and their relation to each other.

_Table 1 - List of entities._

| Concept    | Subconcept   | Meaning                                                                                                                                                                                                                      |
|------------|--------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Slide show |              | The slide show contains an ordered sequence of slides. The presenter navigates through the slide show. The slide show is aware of meta information concerning its author, its title, and the date of creation.               |
| Slide      |              | A slide is owned by a slide show and contains content. A slide is also aware of meta information regarding the title of the slide show it is contained in, and the order in which the slide is positioned in the slide show. |
| Item       |              | An item is a generic term belonging to a slide or to other types of items. It encapsulates a form of information with a specified position.                                                                                  |
|            | &rarr; Text  | Derived from item. A text item owns an ordered sequence of characters.                                                                                                                                                       |
|            | &rarr; List  | Derived from item. A list owns an ordered sequence of arbitrary types of items.                                                                                                                                              |
|            | &rarr; Image | Derived from item. An image displays a fetched figure using a reference to the source.                                                                                                                                       |
|            | &rarr; Table | Derived from item. A table consists of an ordered sequence of rows, that each contain an ordered sequence of arbitrary items.                                                                                                |
| Style      |              | The style controls the appearance of the slide show and its slides by attaching styles to item entities.                                                                                                                     |

A visually depicted model of the entities in table 1 is shown in figure 1. It is noteworthy to mention that the model is not part of the design of the domain, but rather part of the analysis of the domain. It is a direct translation of the entities and their relationships in terms of cardinality and ownership. 

_Figure 1 - Analysis of the domain._

![Domain model](resources/DFC_domain_model.svg)

### Actions
Actions describe the direct consequences that interacting with the domain can have on the real world. To perform an action, it is necessary to provide information. Table 2 shows the set of discernible actions that were formulated through the analysis of the domain.

_Table 2 - List of actions associated with the domain._

| Action              | Meaning                                                           |
|---------------------|-------------------------------------------------------------------|
| 1. Start slide show | Start the slide show at the first slide.                          |
| 2. End slide show   | End the slide show after the final slide has been displayed.      |
| 3. Change slide     | Changes the current slide of the slide show to a different slide. |
 
To elaborate on the actions described in table 2, additional information was formulated and decided upon. These details take into account who the initiator of the action is, when the action takes place, which rules to adhere to before and during the execution of an action, with whom the action is related, and additional information needed to perform the action. Consider the following tables 3, 4, and 5 for such details.  

_Table 3 - Details of the **start slide show action**._

| Aspect      | Details                                       |
|-------------|-----------------------------------------------|
| Initiator   | Presenter                                     |
| When        | At any time                                   |
| Rules       | State projector rule                          |
| Related     | Slide show, Slide                             |
| Information | The first slide to start on in the slide show |

_Table 4 - Details of the **end slide show action**._

| Aspect      | Details                                               |
|-------------|-------------------------------------------------------|
| Initiator   | Presenter                                             |
| When        | After the final slide of the slide show was displayed |
| Rules       |                                                       |
| Related     | Slide show, Slide                                     |
| Information |                                                       |

_Table 5 - Details of the **change slide action**._

| Aspect      | Details                                                                                       |
|-------------|-----------------------------------------------------------------------------------------------|
| Initiator   | Presenter                                                                                     |
| When        | At any desirable time after the start slide show action, but before the end slide show action |
| Rules       | Slide show navigation rule                                                                    |
| Related     | Slide show, Slide                                                                             |
| Information | The next and/or previous slide to show                                                        |

### Rules
The details of the actions can optionally depict associated rules that, in the case of action rules, determine when an action may take place, and strategies, that determine how an action should be performed. 

#### State projector rule
This rule is a *constraint* rule for the **start slide show action**. It specifies that there should be a valid projector that can use the slide show to interface the content of the slides to both the presenter and the viewers.

#### Slide show navigation rule
this rule is a *strategy* rule for the **change slide action**. It specifies that the navigation through the slide show should be from the first slide to the last slide in a linear way. To be able to navigate to a next or previous slide, there should be a valid slide available that corresponds to the requested action.

## Design of the domain

### Entity responsibilities 
The responsibilities of the entities define what the entity knows and what it can do.

#### Slideshow

| Type | Responsibility              | Comments                                            |
|------|-----------------------------|-----------------------------------------------------|
| Know |                             |                                                     |
|      | slides                      | collection of slides                                |
|      | meta information            | title, author, date                                 |
| Can  |                             |                                                     |
|      | navigation trough slides    |                                                     |

#### Slide

| Type | Responsibility              | Comments                                            |
|------|-----------------------------|-----------------------------------------------------|
| Know |                             |                                                     |
|      | items                       | collection of items                                 |
|      |                             |                                                     |
| Can  |                             |                                                     |
|      | navigation trough items     |                                                     |

#### Item

| Type | Responsibility              | Comments                                            |
|------|-----------------------------|-----------------------------------------------------|
| Know |                             |                                                     |
|      | depth                       | structuring depth                                   |
|      | styles                      | collection of styles e.g. color or size             |
|      | items                       | only in case of composite items like list and table |
| Can  |                             |                                                     |
|      |                             |                                                     |

#### Style

| Type | Responsibility              | Comments                                            |
|------|-----------------------------|-----------------------------------------------------|
| Know |                             |                                                     |
|      | style type                  | e.g. color or font                                  |
|      |                             |                                                     |
| Can  |                             |                                                     |
|      | return style information    |                                                     |

### Mapping domain to patterns

#### Composite

| Class              | Role in the pattern | Comments                                      |
|--------------------|---------------------|-----------------------------------------------|
| SlideShowComponent | Component           | abstract class                                |
| SlideShowComposite | Composite           | abstract class                                |
| List               | Composite           | concrete class                                |
| SlideShow          | Composite           | concrete class, only one may exist            |
| Slide              | Composite           | concrete class                                |
| SlideItem          | Leaf                | abstract class                                |
| TextItem           | Leaf                | concrete class                                |
| ImageItem          | Leaf                | concrete class                                | 

#### Iterator

| Class              | Role in the pattern | Comments                                      |
|--------------------|---------------------|-----------------------------------------------|
| Iterator           | Iterator            | interface class                               |
| SlideShowIterator  | Concrete Iterator   |                                               |
| SlideIterator      | Concrete Iterator   |                                               |
| Iterable           | Aggregate           |                                               |
| SlideShow          | Concrete Aggregate  |                                               |
| Slide              | Concrete Aggregate  |                                               |
| List               | Concrete Aggregate  |                                               |
| Table              | Concrete Aggregate  |                                               |           


#### Strategy
| Class              | Role in the pattern | Comments                                      |
|--------------------|---------------------|-----------------------------------------------|
| SlideShowComponent | Composition         |                                               |
| Style              | Compositor          | interface class                               | 
| FontStyle          | Concrete Compositor |                                               |
| ColorStyle         | Concrete Compositor |                                               |

### Considerations
There were a couple of deliberations during the composition of the ubiquitous language, that would be beneficial to discuss. 

In the domain of presentation tools, slides used to be projected using a projector. As an entity, a projector does not fit in the ubiquitous language, because it does not hold much domain specific value other than displaying the slides to a screen. The projector would act as the user-interface of the program, and should therefore be contained in its own layer in the architecture. 

Another entity that was considered, but ultimately removed from the ubiquitous language, is the presenter. The presenter acts as the catalyst to perform navigation actions. However, the presenter is simply the controller of the slideshow, and does not provide domain specific functionality. Therefore, we opted to separate the presenter from the core domain by having the controls layer of the architecture behaving like the presenter. 

It is considered to add an abstract Accessor class as parent of Reader and Writer. That way shared Reader/Writer method could be added to the Accessor instead of adding the FileUtils class. This would make the implementation of DomainDirector more complicated as now the DomainDirector can have different reader/writer protocols at the same time. Otherwise, it would be slightly harder to differentiate between a reader Accessor and a writer Accessor.

The image item class has a BufferedImage object to store the raw image data. This leads to strong coupling with java.awt.image package. We acknowledge the strong coupling as we made this decision consciously. The reason being that the other alternative was to lazily load the image during the rendering of the slide. This would not behave like other presentation programs. Optionally another alternative is to use another way to obtain the raw image data, and thereafter store it in a native data structure (i.e., an array).

Use of the visitor pattern. The visitor pattern is applied on the slideshow domain components and on the style components. It allows for the traversal operation on a complex object to be delegated to its own class with its own interface. Rather than defining the traversal and behavior in each of the domain objects, the visitor delegates this behavior. The advantage is that new operations are easily added. Consider the renderer and the serializer. However, the disadvantage is that it can be difficult to maintain multiple implementations of the visitor interface when the domain is expanded with new composites or items. Each visitor would then have to be changed. This is a consideration that was made, and concluded that the flexibility of having delegated behavior of the traversal of the domain outweighs the inflexibility of maintaining multiple visitors. Moreover, the style visitor is meant to prepare for future styles where possibly a style can consist of multiple styles. Therefore, needing the support to traverse the styling tree structure.    

## Design of the creation of objects (and reading and writing)

## Design notes:
- For the UI, we use a combination of the Command pattern and the Observer pattern. this seems to be overkill. However, in the face of extendibility, one could imagine the observable (i.e., the keyboard controller) to notify not only a slideshow projector, but also many other devices to allow streaming/screen-sharing functionality. In the same vein, commands are initialised in the keyboard controller, with a passed reference to the current projector, then updated by the same projector during an observer notification, and then executed, which delegates command execution back and forth. This also seems like overkill, however, it serves the purpose of extending commands with functionality that is not only linked to the projector, but for instance to the styling of the slide show components (making every character larger for example).
- A Builder pattern is used to support future slideshow variants like an infographic, which consist of exactly one slide and more interactive slideshows. Instead of creating several make methods to call a specific (future) builder, the design implements a strategy pattern for the Director in order to segregate builders from eachother. This will prevent a large DomainDirector when DomainBuilders are added, as well specific methods can be added to the concrete DomainDirector. 
- A Bridge pattern between a concrete DomainBuilder and Deserializer is used to support future deserialization protocols. This is preferred over creating concrete protocol builders like XMLSlideShowBuilder because in combination with the Builder pattern this whould result in duplicated code when creating for example a XMLInfographicBuilder. With the bridge pattern both SlideShowBuilder and InfographicBuilder are able to use XMLReader. 
