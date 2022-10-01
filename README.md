# Jabberpoint Project
Jabberpoint is a slide show presentation tool developed for the master course Design for Change at the Open University. The project is meant to demonstrate the process of creating software in a future-proof way, by using an agreed upon ubiquitous language, applying common Object-Oriented design patterns, and following several design principles.

## Ubiquitous language
The analysis of the domain yielded a set of entities, actions and rules, that can be seen as the dictionary and the grammar of the domain. Through the use of this language, it is easier to reason about certain aspects of the domain, which will contribute to the design of insightful models and their placement in the architecture. 

### Entities
The following table depicts the entities, their meaning with regard to the domain, possible sub concepts related to an entity, and their relation to each other.

_Table 1 - List of entities._

| Concept    | Subconcept   | Meaning                                                                                                                                                                                                                                     |
|------------|--------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Projector  |              | The projector provides capabilities to interface with the display, inject an interchangeable slide show, and show a slide show on the display.                                                                                              |
| Presenter  |              | The presenter is responsible for the creation of a slide show and for starting the slide show through the projector. Furthermore, it is expected from a presenter to actively navigate through the slide show                               |
| Slide show |              | The slide show contains an ordered sequence of slides. The presenter owns a slide show, and navigates through the slide show. The slide show is aware of meta information concerning its presenter, its title, and the date of creation.    |
| Slide      |              | A slide is owned by a slide show and contains content. A slide is also aware of meta information regarding the title of the slide show it is contained in, and the number of the slide in the ordered sequence of slides in the slide show. |
| Content    |              | Content belongs to a single slide and is a generic term that comprises a form of information with a specified position on a slide.                                                                                                          |
|            | &rarr; Text  | Derived from content. Text owns an ordered sequence of characters.                                                                                                                                                                          |
|            | &rarr; List  | Derived from content. A list owns an ordered sequence of content entities that can be nested arbitrarily.                                                                                                                                   |
|            | &rarr; Image | Derived from content. An image displays a fetched figure through a reference to the source.                                                                                                                                                 |
|            | &rarr; Table | Derived from content. A table consists of an ordered sequence of rows, that each contain an ordered sequence of content entities.                                                                                                           |
| Style      |              | The style controls the appearance of the slide show and its slides by being attached to content entities.                                                                                                                                   |

A visually depicted model of the entities in table 1 is shown in figure 1. It is noteworthy to mention that the model is not part of the design of the domain, but rather part of the analysis of the domain. It is a direct translation of the entities and their relationship in terms of cardinality and ownership. 

_Figure 1 - Analysis of the domain._

![Domain model](resources/DFC_domain_model.svg)

### Actions
Actions describe the direct consequences that interacting with the domain can have on the real world. To perform an action, it is necessary to provide information. Table 2 shows the set of discernible actions that were formulated through the analysis of the domain.

_Table 2 - List of actions associated with the domain._

| Action              | Meaning                                                                |
|---------------------|------------------------------------------------------------------------|
| 1. Start slide show | Start the slide show by providing a valid slide show to the projector. |
| 2. End slide show   | End the slide show after the final slide has been displayed.           |
| 3. Change slide     | Changes the current slide of the slide show to a different slide.      |
 
To elaborate on the actions described in table 2, additional information was formulated and decided upon. These details take into account who the initiator of the action is, when the action takes place, which rules to adhere to before and during the execution of an action, with whom the action is related, and additional information needed to perform the action. Consider the following tables 3, 4, and 5 for such details.  

_Table 3 - Details of the **start slide show action**._

| Aspect      | Details                                       |
|-------------|-----------------------------------------------|
| Initiator   | Presenter                                     |
| When        | At any time                                   |
| Rules       | State projector rule                          |
| Related     | Slide show, Slide, Projector                  |
| Information | The first slide to start on in the slide show |

_Table 4 - Details of the **end slide show action**._

| Aspect      | Details                                               |
|-------------|-------------------------------------------------------|
| Initiator   | Presenter                                             |
| When        | After the final slide of the slide show was displayed |
| Rules       |                                                       |
| Related     | Slide show, Slide, Projector                          |
| Information |                                                       |

_Table 5 - Details of the **change slide action**._

| Aspect      | Details                                                                                       |
|-------------|-----------------------------------------------------------------------------------------------|
| Initiator   | Presenter                                                                                     |
| When        | At any desirable time after the start slide show action, but before the end slide show action |
| Rules       | Slide show navigation rule                                                                    |
| Related     | Slide show, Slide, Projector                                                                  |
| Information | The next and/or previous slide to show                                                        |

### Rules
The details of the actions can optionally depict associated rules that, in the case of action rules, determine when an action may take place, and strategies, thatdetermine how an action should be performed. 

#### State projector rule
This rule is a *constraint* rule for the **start slide show action**. It specifies that there should be no previous slide show in the projector, and can be enforced by always overriding the current slide show in the projector with a provided slide show. 

#### Slide show navigation rule
this rule is a *strategy* rule for the **change slide action**. It specifies that the navigation through the slide show should be from the first slide to the last slide in a linear way. To be able to navigate to a next or previous slide, there should be a valid slide available that corresponds to the requested action.

