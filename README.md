# Jabberpoint Project
Jabberpoint is a slide show presentation tool developed for the master course Design for Change at the Open University. The project is meant to demonstrate the process of creating software in a future-proof way, by using an agreed upon ubiquitous language, applying common Object-Oriented design patterns, and following several design principles.

## Ubiquitous language
The analysis of the domain yielded a set of entities, actions and rules, that can be seen as the dictionary and the grammar of the domain. Through this language, it is easier to reason about certain aspects of the domain, which will help design an architecture and its models. 

### Entities
The following table depicts the entities, their meaning with regard to the domain, possible sub concepts related to an entity, and their relation to each other.

_Table 1 - List of entities_

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

_Figure 1 - Analysis of the domain_

![Domain model](resources/DFC_domain_model.svg)

### Actions
Actions describe the direct consequences that interacting with the domain can have on the real world. To perform an action, it is necessary to provide information.  
