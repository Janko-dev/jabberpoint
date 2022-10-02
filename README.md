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
This rule is a *constraint* rule for the **start slide show action**. It specifies that there should be a valid projector that can use the slide show to interface the content of the slides to both the presentor and the viewers.

#### Slide show navigation rule
this rule is a *strategy* rule for the **change slide action**. It specifies that the navigation through the slide show should be from the first slide to the last slide in a linear way. To be able to navigate to a next or previous slide, there should be a valid slide available that corresponds to the requested action.

### Considerations
There were a couple of deliberations during the composition of the ubiquitous language, that would be beneficial to discuss. 

In the domain of presentation tools, slides used to be projected using a projector. As an entity, a projector did not fit in the ubiquitous language, because it doesn't hold much domain specific value other than displaying the slides to a screen. The projector would act as the user-interface of the program, and should therefore be contained in its own layer in the architecture. 

Another entity that was considered, but ultimately removed from the ubiquitous language, is the presenter. The presenter acts as the catalyst to perform navigation actions. However, the presenter is simply the controller of the slideshow, and does not provide domain specific functionality. Therefore, we opted to separate the presenter from the core domain by placing the presenter in a controls layer. 

## Design of the domain

