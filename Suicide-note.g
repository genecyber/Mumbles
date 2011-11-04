Date: Thu, 16 Oct 1997 19:18:42 -0700 (PDT)
X-Sender: austin@pobox3.stanford.edu
X-Mailer: Windows Eudora Light Version 1.5.2
To: zelenski@CS.Stanford.EDU
From: Austin Che <austin@stanford.edu>
Subject: just another grammar
X-Attachments: Z:\PROGRA~1\CS107\HW1C\GRAMMARS\SUICIDE.G;

The assignment and the class definitely inspired me to write this...
Generates a suicide note.
Idea derived from Online Suicide Note Generator found on the Internet
Written by Austin Che and inspired by CS107

{
<start>
<salutation>
I want to <intent> <when> because <reason> . 
I'm sure <who> will be <reaction> to hear of my death.
Please dispose of my corpse by <disposal> and
give <what> to <recipient> .
<closing>	;
}


{
<salutation>
Dear <person> ,		;
To my beloved <person> :		;
To whom it does not concern:	;
Oh, <negativeFeeling> world!	;
}

{
<closing>
May I rest in peace.	;
Take me away!			;
My destiny awaits.		;
Goodbye <negativeFeeling> world.	;
See you in the next life.	;
See you in Heaven.			;
See you in Hell.			;
}

{
<negativeFeeling>
<adjective> , <adjective>	;
<adjective>		;
}

{
<adjective>
boring          ;
cheerless       ;
cruel           ;
depressing      ;
desolate        ;
despicable		;
devilish        ;
dreary          ;
dull            ;
harsh           ;
heartless       ;
lonely          ;
mean            ;
miserable		;
monotonous      ;
pitiful			;
relentless      ;
satanic         ;
savage          ;
tedious         ;
tiresome        ;
uncaring        ;
unhappy			;
unkind          ;
wearisome       ;
weary           ;
worthless		;
wretched        ;
}

{
<person>
Mom					;
Dad					;
son					;
daughter			;
wife				;
husband				;
dog					;
fish				;
friends and family	;
Diary				;
God					;
Satan				;
}

{
<intent>
kill myself		;
go to sleep for eternity	;
go explore a new place 		;
jump off the roof	;
jump off a bridge	;
hang myself			;
starve myself		;
dissect my brain	;
eat my heart		;
live on the bottom of the ocean		;
go skydiving without a parachute	;
}

{
<when>
now			;
today		;
tomorrow	;
soon		;
sometime or another	;
}

{
<reason>
Hell sounds interesting	;
Hell would be more fun than this place	;
death sounds like fun	;
I like living on the edge	;
everyone else is doing it	;
life is boring	;
my religion tells me to	;
I have learned everything there is to learn here	;
of sheer spite	;
between computer science and Hell, I choose the latter	;
it's either death or computer science	;
}

{
<who>
everyone	;
no one		;
you			;
Satan		;
my pets		;
the insurance company	;
}

{
<reaction>
happy		;
ecstatic	;
glad		;
delighted	;
exhilarated	;
overjoyed	;
sad			;
hurt		;
sorry		;
regretful	;
irritated	;
angry		;
completely apathetic	;
}

{
<disposal>
throwing a Donner party	;
selling pieces to the Ferengi	;
cremation	;
next week	;
letting it rot	;
using it instead of that unlabeled meat in dorms	;
}

{
<what>
everything I own	;
nothing	at all		;
what's left of my body		;
my heart	;
my soul		;
}

{
<recipient>
charity		;
my parents	;
my dog		;
my fish		;
my professors	;
my enemies	;
my roommate		;
the government	;
the beggar down the street who needs it more than I	;
}
