# GildedRoseKata

Attempt to refactor GildedRoseKata
At first I was trying to create whole new app, with items in DB
also item properties entity, to keep specific properties for item or for item class, and item classification table.
then service would get all items, clasify them, them process acording properties that apply to class ir item itself.
This was not fulfiled as I was more thinking about RDB, that is more dificult to implement because lots of diferent 
properties and that they can be added.
Next problem that was to much time spent on making elasticsearch work. Once couple years ago I was trying it successfully and 
was devastating not being able make it to work now, trying different combinations of springboot and elasticsearch.
Then I came to what has to be done - follow the steps, first refactor. Never done this type before, till the end I 
understood how it works, cameout almost OK, do not like static methods in interface.
Have no expierence with asynch processing, only used it in rest methods to process more simultaneous requests at once.
Tried implementing MongoDB, that seems easy, but have no expierence in that, and seems not working still how it should.
Scheduled anotation used to process once a day. Should be some property when item was updated to not update it more than 
once or to updated more if some days were missed. Think DAO should be used to communicate with db and convert to non-changeable Item.
