package com.salomatin.alex.militaryquotes;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.salomatin.alex.militaryquotes.music.HomeWatcher;
import com.salomatin.alex.militaryquotes.music.MusicService;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuote;
    private Button nextQuote;
    boolean visible;

    HomeWatcher mHomeWatcher;

    int selector;
    ViewGroup transitionsContainer;

    // List of random quotes
    ArrayList<String> listOfQuotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BIND Music Service
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        //Start HomeWatcher
        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }

            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        mHomeWatcher.startWatch();


        // Set transition
        transitionsContainer = findViewById(R.id.transitions_container);

        // Set background music

        listOfQuotes = new ArrayList<String>();
        listOfQuotes.add("\"Never in the field of human conflict was so much owed by so many to so few.\"\n" +
                "— Winston Churchill");
        listOfQuotes.add("\"Success is not final, failure is not fatal: it is the courage to continue that counts.\"\n" +
                "— Winston Churchill");
        listOfQuotes.add("\"In war there is no prize for the runner-up.\"\n" +
                "— General Omar Bradley");
        listOfQuotes.add("\"The soldier above all others prays for peace, for it is the soldier who must suffer and bear the deepest wounds and scars of war.\"\n" +
                "— General Douglas MacArthur");
        listOfQuotes.add("\"They died hard, those savage men - like wounded wolves at bay. They were filthy, and they were lousy, and they stunk. And I loved them.\"\n" +
                "— General Douglas MacArthur");
        listOfQuotes.add("\"We must be prepared to make heroic sacrifices for the cause of peace that we make ungrudgingly for the cause of war. There is no task that is more important or closer to my heart.\"\n" +
                "— Albert Einstein");
        listOfQuotes.add("\"War is an ugly thing, but not the ugliest of things. The decayed and degraded state of moral and patriotic feeling which thinks that nothing is worth war is much worse. The person who has nothing for which he is willing to fight, nothing which is more important than his own personal safety, is a miserable creature, and has no chance of being free unless made or kept so by the exertions of better men than himself.\"\n" +
                "— John Stuart Mill");
        listOfQuotes.add("\"Future years will never know the seething hell and the black infernal background, the countless minor scenes and interiors of the secession war; and it is best they should not. The real war will never get in the books.\"\n" +
                "— Walt Whitman");
        listOfQuotes.add("\"There's no honorable way to kill, no gentle way to destroy. There is nothing good in war. Except its ending.\"\n" +
                "— Abraham Lincoln");
        listOfQuotes.add("\"The death of one man is a tragedy. The death of millions is a statistic.\"\n" +
                "— Joseph Stalin");
        listOfQuotes.add("\"Death solves all problems - no man, no problem.\"\n" +
                "— Joseph Stalin");
        listOfQuotes.add("\"In the Soviet army it takes more courage to retreat than advance.\"\n" +
                "— Joseph Stalin");
        listOfQuotes.add("\"It is foolish and wrong to mourn the men who died. Rather we should thank God that such men lived.\"\n" +
                "— General George S. Patton");
        listOfQuotes.add("\"Never think that war, no matter how necessary, nor how justified, is not a crime.\"\n" +
                "— Ernest Hemingway");
        listOfQuotes.add("\"Every man's life ends the same way. It is only the details of how he lived and how he died that distinguish one man from another.\"\n" +
                "— Ernest Hemingway");
        listOfQuotes.add("\"All wars are civil wars, because all men are brothers.\"\n" +
                "— Francois Fenelon");
        listOfQuotes.add("\"I have never advocated war except as a means of peace.\"\n" +
                "— Ulysses S. Grant");
        listOfQuotes.add("\"Older men declare war. But it is the youth that must fight and die.\"\n" +
                "— Herbert Hoover");
        listOfQuotes.add("\"Only the dead have seen the end of war.\"\n" +
                "— Plato");
        listOfQuotes.add("\"Death is nothing, but to live defeated and inglorious is to die daily.\"\n" +
                "— Napoleon Bonaparte");
        listOfQuotes.add("\"It is well that war is so terrible, or we should get too fond of it.\"\n" +
                "— Robert E. Lee");
        listOfQuotes.add("\"A soldier will fight long and hard for a bit of colored ribbon.\"\n" +
                "— Napoleon Bonaparte");
        listOfQuotes.add("\"He who fears being conquered is sure of defeat.\"\n" +
                "— Napoleon Bonaparte");
        listOfQuotes.add("\"You must not fight too often with one enemy, or you will teach him all your art of war.\"\n" +
                "— Napoleon Bonaparte");
        listOfQuotes.add("\"There's a graveyard in northern France where all the dead boys from D-Day are buried. The white crosses reach from one horizon to the other. I remember looking it over and thinking it was a forest of graves. But the rows were like this, dizzying, diagonal, perfectly straight, so after all it wasn't a forest but an orchard of graves. Nothing to do with nature, unless you count human nature.\"\n" +
                "— Barbara Kingsolver");
        listOfQuotes.add("\"If we don't end war, war will end us.\"\n" +
                "— H. G. Wells");
        listOfQuotes.add("\"From my rotting body, flowers shall grow and I am in them and that is eternity.\"\n" +
                "— Edvard Munch");
        listOfQuotes.add("\"He who has a thousand friends has not a friend to spare, And he who has one enemy will meet him everywhere.\"\n" +
                "— Ali ibn-Abi-Talib");
        listOfQuotes.add("\"For the Angel of Death spread his wings on the blast, And breathed in the face of the foe as he pass'd; And the eyes of the sleepers wax'd deadly and chill, And their hearts but once heaved, and for ever grew still!\"\n" +
                "— George Gordon Byron, The Destruction of Sennacherib");
        listOfQuotes.add("\"They wrote in the old days that it is sweet and fitting to die for one's country. But in modern war, there is nothing sweet nor fitting in your dying. You will die like a dog for no good reason.\"\n" +
                "— Ernest Hemingway");
        listOfQuotes.add("\"There is many a boy here today who looks on war as all glory, but, boys, it is all hell. You can bear this warning voice to generations yet to come. I look upon war with horror.\"\n" +
                "— General William Tecumseh Sherman");
        listOfQuotes.add("\"War is as much a punishment to the punisher as it is to the sufferer.\"\n" +
                "— Thomas Jefferson");
        listOfQuotes.add("\"War would end if the dead could return.\"\n" +
                "— Stanley Baldwin");
        listOfQuotes.add("\"When you have to kill a man it costs nothing to be polite.\"\n" +
                "— Winston Churchill");
        listOfQuotes.add("\"Battles are won by slaughter and maneuver. The greater the general, the more he contributes in maneuver, the less he demands in slaughter.\"\n" +
                "— Winston Churchill");
        listOfQuotes.add("\"History will be kind to me for I intend to write it.\"\n" +
                "— Winston Churchill");
        listOfQuotes.add("\"We shall defend our island, whatever the cost may be, we shall fight on the beaches, we shall fight on the landing grounds, we shall fight in the fields and in the streets, we shall fight in the hills; we shall never surrender.\"\n" +
                "— Winston Churchill");
        listOfQuotes.add("\"When you get to the end of your rope, tie a knot and hang on.\"\n" +
                "— Franklin D. Roosevelt");
        listOfQuotes.add("\"A hero is no braver than an ordinary man, but he is brave five minutes longer.\"\n" +
                "— Ralph Waldo Emerson");
        listOfQuotes.add("\"Our greatest glory is not in never failing, but in rising up every time we fail.\"\n" +
                "— Ralph Waldo Emerson");
        listOfQuotes.add("\"The characteristic of a genuine heroism is its persistency. All men have wandering impulses, fits and starts of generosity. But when you have resolved to be great, abide by yourself, and do not weakly try to reconcile yourself with the world. The heroic cannot be the common, nor the common the heroic.\"\n" +
                "— Ralph Waldo Emerson");
        listOfQuotes.add("\"If the opposition disarms, well and good. If it refuses to disarm, we shall disarm it ourselves.\"\n" +
                "— Joseph Stalin");
        listOfQuotes.add("\"The object of war is not to die for your country but to make the other bastard die for his.\"\n" +
                "— General George S. Patton");
        listOfQuotes.add("\"Better to fight for something than live for nothing.\"\n" +
                "— General George S. Patton");
        listOfQuotes.add("\"Courage is fear holding on a minute longer.\"\n" +
                "— General George S. Patton");
        listOfQuotes.add("\"We happy few, we band of brothers/For he today that sheds his blood with me/Shall be my brother.\"\n" +
                "— William Shakespeare,’’ King Henry V’’");
        listOfQuotes.add("\"Cowards die many times before their deaths; The valiant never taste of death but once.\"\n" +
                "— William Shakespeare, ‘’Julius Caesar’’");
        listOfQuotes.add("\"Never interrupt your enemy when he is making a mistake.\"\n" +
                "— Napoleon Bonaparte");
        listOfQuotes.add("\"There are only two forces in the world, the sword and the spirit. In the long run the sword will always be conquered by the spirit.\"\n" +
                "— Napoleon Bonaparte");
        listOfQuotes.add("\"There will one day spring from the brain of science a machine or force so fearful in its potentialities, so absolutely terrifying, that even man, the fighter, who will dare torture and death in order to inflict torture and death, will be appalled, and so abandon war forever.\"\n" +
                "— Thomas A. Edison");
        listOfQuotes.add("\"There are no atheists in foxholes, this isn't an argument against atheism, it's an argument against foxholes.\"\n" +
                "— James Morrow");
        listOfQuotes.add("\"Live as brave men; and if fortune is adverse, front its blows with brave hearts.\"\n" +
                "— Cicero");
        listOfQuotes.add("\"Courage and perseverance have a magical talisman, before which difficulties disappear and obstacles vanish into air.\"\n" +
                "— John Quincy Adams");
        listOfQuotes.add("\"Courage is being scared to death - but saddling up anyway.\"\n" +
                "— John Wayne");
        listOfQuotes.add("\"Above all things, never be afraid. The enemy who forces you to retreat is himself afraid of you at that very moment.\"\n" +
                "— Andre Maurois");
        listOfQuotes.add("\"I have never made but one prayer to God, a very short one: 'O Lord, make my enemies ridiculous.' And God granted it.\"\n" +
                "— Voltaire");
        listOfQuotes.add("\"Safeguarding the rights of others is the most noble and beautiful end of a human being.\"\n" +
                "— Kahlil Gibran, ‘’The Voice of the Poet’’");
        listOfQuotes.add("\"He conquers who endures.\"\n" +
                "— Persius");
        listOfQuotes.add("\"It is better to die on your feet than to live on your knees!\"\n" +
                "— Emiliano Zapata");
        listOfQuotes.add("\"You know the real meaning of peace only if you have been through the war.\"\n" +
                "— Kosovar");
        listOfQuotes.add("\"Those who have long enjoyed such privileges as we enjoy forget in time that men have died to win them.\"\n" +
                "— Franklin D. Roosevelt");
        listOfQuotes.add("\"In war there is no substitute for victory.\"\n" +
                "— General Douglas MacArthur");
        listOfQuotes.add("\"War is a series of catastrophes which result in victory.\"\n" +
                "— Georges Clemenceau");
        listOfQuotes.add("\"In war, truth is the first casualty\"\n" +
                "— Aeschylus");
        listOfQuotes.add("\"Incoming fire has the right of way.\"\n" +
                "— Unknown");
        listOfQuotes.add("\"Mankind must put an end to war, or war will put an end to mankind.\"\n" +
                "— John F. Kennedy");
        listOfQuotes.add("\"War does not determine who is right, only who is left.\"\n" +
                "— Bertrand Russell");
        listOfQuotes.add("\"A ship without Marines is like a garment without buttons.\"\n" +
                "— Admiral David D. Porter, USN");
        listOfQuotes.add("\"The press is our chief ideological weapon.\"\n" +
                "— Nikita Khrushchev");
        listOfQuotes.add("\"Whether you like it or not, history is on our side. We will bury you!\"\n" +
                "— Nikita Khrushchev");
        listOfQuotes.add("\"If the enemy is in range, so are you.\"\n" +
                "— Infantry Journal");
        listOfQuotes.add("\"So long as there are men, there will be wars.\"\n" +
                "— Albert Einstein");
        listOfQuotes.add("\"Aim towards the Enemy.\"\n" +
                "— Instruction printed on US Rocket Launcher");
        listOfQuotes.add("\"I think the human race needs to think about killing. How much evil must we do to do good?\"\n" +
                "— Robert McNamara");
        listOfQuotes.add("\"Any military commander who is honest will admit he makes mistakes in the application of military power.\"\n" +
                "— Robert McNamara");
        listOfQuotes.add("\"You can make a throne of bayonets, but you cant sit on it for long.\"\n" +
                "— Boris Yeltsin");
        listOfQuotes.add("\"The deadliest weapon in the world is a Marine and his rifle!\"\n" +
                "— General John J. Pershing");
        listOfQuotes.add("\"Concentrated power has always been the enemy of liberty.\"\n" +
                "— Ronald Reagan");
        listOfQuotes.add("\"Nothing in life is so exhilarating as to be shot at without result.\"\n" +
                "— Winston Churchill");
        listOfQuotes.add("\"War is delightful to those who have not yet experienced it.\"\n" +
                "— Erasmus");
        listOfQuotes.add("\"Friendly fire, isn't.\"\n" +
                "— Unknown");
        listOfQuotes.add("\"Diplomats are just as essential in starting a war as soldiers are for finishing it.\"\n" +
                "— Will Rogers");
        listOfQuotes.add("\"I think that technologies are morally neutral until we apply them. It's only when we use them for good or for evil that they become good or evil.\"\n" +
                "— William Gibson");
        listOfQuotes.add("\"All that is necessary for evil to succeed is for good men to do nothing.\"\n" +
                "— Edmund Burke");
        listOfQuotes.add("\"The commander in the field is always right and the rear echelon is wrong, unless proved otherwise.\"\n" +
                "— Colin Powell");
        listOfQuotes.add("\"Freedom is not free, but the U.S. Marine Corps will pay most of your share.\"\n" +
                "— Ned Dolan");
        listOfQuotes.add("\"I know not with what weapons World War III will be fought, but World War IV will be fought with sticks and stones.\"\n" +
                "— Albert Einstein");
        listOfQuotes.add("\"The truth of the matter is that you always know the right thing to do. The hard part is doing it.\"\n" +
                "— Norman Schwarzkopf");
        listOfQuotes.add("\"If you know the enemy and know yourself you need not fear the results of a hundred battles.\"\n" +
                "— Sun Tzu");
        listOfQuotes.add("\"Nearly all men can stand adversity, but if you want to test a man's character, give him power.\"\n" +
                "— Abraham Lincoln");
        listOfQuotes.add("\"If we can't persuade nations with comparable values of the merits of our cause, we'd better reexamine our reasoning.\"\n" +
                "— Robert McNamara");
        listOfQuotes.add("\"The tree of liberty must be refreshed from time to time with the blood of patriots and tyrants.\"\n" +
                "— Thomas Jefferson");
        listOfQuotes.add("\"If the wings are traveling faster than the fuselage, it's probably a helicopter, and therefore, unsafe.\"\n" +
                "— Unknown");
        listOfQuotes.add("\"Five second fuses only last three seconds.\"\n" +
                "— Infantry Journal");
        listOfQuotes.add("\"If your attack is going too well, you're walking into an ambush.\"\n" +
                "— Infantry Journal");
        listOfQuotes.add("\"No battle plan survives contact with the enemy.\"\n" +
                "— Colin Powell");
        listOfQuotes.add("\"When the pin is pulled, Mr. Grenade is not our friend.\"\n" +
                "— U.S. Army Training Notice");
        listOfQuotes.add("\"A man may die, nations may rise and fall, but an idea lives on.\"\n" +
                "— John F. Kennedy");
        listOfQuotes.add("\"A leader leads by example, not by force.\"\n" +
                "— Sun Tzu");
        listOfQuotes.add("\"If you can't remember, the claymore is pointed toward you.\"\n" +
                "— Unknown");
        listOfQuotes.add("\"There are only two kinds of people that understand Marines: Marines and the enemy. Everyone else has a secondhand opinion.\"\n" +
                "— General William Thornson");
        listOfQuotes.add("\"The more marines I have around, the better I like it.\"\n" +
                "— General Clark, U.S. Army");
        listOfQuotes.add("\"Never forget that your weapon was made by the lowest bidder.\"\n" +
                "— Unknown");
        listOfQuotes.add("\"Keep looking below surface appearances. Don't shrink from doing so just because you might not like what you find.\"\n" +
                "— Colin Powell");
        listOfQuotes.add("\"Try to look unimportant; they may be low on ammo.\"\n" +
                "— Infantry Journal");
        listOfQuotes.add("\"The world will not accept dictatorship or domination.\"\n" +
                "— Mikhail Gorbachev");
        listOfQuotes.add("\"Tyrants have always some slight shade of virtue; they support the laws before destroying them.\"\n" +
                "— Voltaire");
        listOfQuotes.add("\"Some people live an entire lifetime and wonder if they have ever made a difference in the world, but the Marines don't have that problem.\"\n" +
                "— Ronald Reagan");
        listOfQuotes.add("\"It is generally inadvisable to eject directly over the area you just bombed.\"\n" +
                "— U.S. Air Force Marshal");
        listOfQuotes.add("\"We sleep safely in our beds because rough men stand ready in the night to visit violence on those who would harm us.\"\n" +
                "— Richard Grenier");
        listOfQuotes.add("\"If at first you don't succeed, call an air strike.\"\n" +
                "— Unknown");
        listOfQuotes.add("\"Tracers work both ways.\"\n" +
                "— U.S. Army Ordinance");
        listOfQuotes.add("\"Teamwork is essential, it gives them other people to shoot at.\"\n" +
                "— Unknown");
        listOfQuotes.add("\"The real and lasting victories are those of peace, and not of war.\"\n" +
                "— Ralph Waldo Emmerson");
        listOfQuotes.add("\"We're in a world in which the possibility of terrorism, married up with technology, could make us very, very sorry we didn't act.\"\n" +
                "— Condoleeza Rice");
        listOfQuotes.add("\"All warfare is based on deception.\"\n" +
                "— Sun Tzu");
        listOfQuotes.add("\"The indefinite combination of human infallibility and nuclear weapons will lead to the destruction of nations.\"\n" +
                "— Robert McNamara");
        listOfQuotes.add("\"In war, you win or lose, live or die and the difference is just an eyelash.\"\n" +
                "— General Douglas MacArthur");
        listOfQuotes.add("\"You can't say civilization don't advance, for in every war, they kill you in a new way.\"\n" +
                "— Will Rogers");
        listOfQuotes.add("\"They'll be no learning period with nuclear weapons. Make one mistake and you're going to destroy nations.\"\n" +
                "— Robert McNamara");
        listOfQuotes.add("\"It doesn't take a hero to order men into battle. It takes a hero to be one of those men who goes into battle.\"\n" +
                "— General Norman Schwarzkopf");
        listOfQuotes.add("\"Any soldier worth his salt should be antiwar. And still, there are things worth fighting for.\"\n" +
                "— General Norman Schwarzkopf");
        listOfQuotes.add("\"It is fatal to enter any war without the will to win it.\"\n" +
                "— General Douglas MacArthur");
        listOfQuotes.add("\"Let your plans be as dark and impenetrable as night, and when you move, fall like a thunderbolt.\"\n" +
                "— Sun Tzu");
        listOfQuotes.add("\"Anyone, who truly wants to go to war, has truly never been there before!\"\n" +
                "— Larry Reeves");
        listOfQuotes.add("\"Whoever said the pen is mightier than the sword obviously never encountered automatic weapons.\"\n" +
                "— General Douglas MacArthur");
        listOfQuotes.add("\"Whoever does not miss the Soviet Union has no heart. Whoever wants it back has no brain.\"\n" +
                "— Vladimir Putin");
        listOfQuotes.add("\"My first wish is to see this plague of mankind, war, banished from the earth.\"\n" +
                "— George Washington");
        listOfQuotes.add("\"Cluster bombing from B-52s are very, very, accurate. The bombs are guaranteed to always hit the ground.\"\n" +
                "— USAF Ammo Troop");
        listOfQuotes.add("\"If a man has done his best, what else is there?\"\n" +
                "— General George S. Patton");
        listOfQuotes.add("\"The bursting radius of a hand-grenade is always one foot greater than your jumping range.\"\n" +
                "— Unknown");
        listOfQuotes.add("\"The tyrant always talks as if he's preserving the best interests of his people when he actually acts to undermine them.\"\n" +
                "— Ramman Kenoun");
        listOfQuotes.add("\"Every tyrant who has lived has believed in freedom - for himself.\"\n" +
                "— Elbert Hubbard");
        listOfQuotes.add("\"If our country is worth dying for in time of war let us resolve that it is truly worth living for in time of peace.\"\n" +
                "— Hamilton Fish");
        listOfQuotes.add("\"Nationalism is an infantile disease. It is the measles of mankind.\"\n" +
                "— Albert Einstein");
        listOfQuotes.add("\"We must not confuse dissent with disloyalty.\"\n" +
                "— Edward R Murrow");
        listOfQuotes.add("\"I will fight for my country, but I will not lie for her.\"\n" +
                "— Zora Neale Hurston");
        listOfQuotes.add("\"As we express our gratitude, we must never forget that the highest appreciation is not to utter words, but to live by them.\"\n" +
                "— John F Kennedy");
        listOfQuotes.add("\"An eye for an eye makes the whole world blind.\"\n" +
                "— Gandhi");
        listOfQuotes.add("\"Traditional nationalism cannot survive the fissioning of the atom. One world or none.\"\n" +
                "— Stuart Chase");
        listOfQuotes.add("\"If you want a symbolic gesture, don't burn the flag; wash it.\"\n" +
                "— Norman Thomas");
        listOfQuotes.add("\"The nation is divided, half patriots and half traitors, and no man can tell which from which.\"\n" +
                "— Mark Twain");
        listOfQuotes.add("\"Patriotism is your conviction that this country is superior to all others because you were born in it.\"\n" +
                "— George Bernard Shaw");
        listOfQuotes.add("\"If you are ashamed to stand by your colors, you had better seek another flag.\"\n" +
                "— Anonymous");
        listOfQuotes.add("\"Revenge, at first though sweet, Bitter ere long back on itself recoils.\"\n" +
                "— John Milton");
        listOfQuotes.add("\"A citizen of America will cross the ocean to fight for democracy, but won't cross the street to vote...\"\n" +
                "— Bill Vaughan");
        listOfQuotes.add("\"Patriotism is supporting your country all the time, and your government when it deserves it.\"\n" +
                "— Mark Twain");
        listOfQuotes.add("\"I love America more than any other country in this world; and, exactly for this reason, I insist on the right to criticize her perpetually.\"\n" +
                "— James Baldwin");
        listOfQuotes.add("\"...dissent, rebellion, and all-around hell-raising remain the true duty of patriots.\"\n" +
                "— Barbara Ehrenreich");
        listOfQuotes.add("\"Principle is OK up to a certain point, but principle doesn't do any good if you lose.\"\n" +
                "— Dick Cheney");
        listOfQuotes.add("\"If an injury has to be done to a man it should be so severe that his vengeance need not be feared.\"\n" +
                "— Machiavelli");
        listOfQuotes.add("\"Before you embark on a journey of revenge, you should first dig two graves.\"\n" +
                "— Confucius");
        listOfQuotes.add("\"It is lamentable, that to be a good patriot one must become the enemy of the rest of mankind.\"\n" +
                "— Voltaire");
        listOfQuotes.add("\"Ask not what your country can do for you, but what you can do for your country.\"\n" +
                "— John F Kennedy");
        listOfQuotes.add("\"Revenge is profitable.\"\n" +
                "— Edward Gibbon");
        listOfQuotes.add("\"Patriotism ruins history.\"\n" +
                "— Goethe");
        listOfQuotes.add("\"I would not say that the future is necessarily less predictable than the past. I think the past was not predictable when it started.\"\n" +
                "— Donald Rumsfeld");
        listOfQuotes.add("\"I only regret that I have but one life to give for my country.\"\n" +
                "— Nathan Hale");
        listOfQuotes.add("\"Live well. It is the greatest revenge.\"\n" +
                "— The Talmud");
        listOfQuotes.add("\"We know where they are. They're in the area around Tikrit and Baghdad and east, west, south and north somewhat.\"\n" +
                "— Donald Rumsfeld");
        listOfQuotes.add("\"I was born an American; I will live an American; I shall die an American!\"\n" +
                "— Daniel Webster");
        listOfQuotes.add("\"Patriotism varies, from a noble devotion to a moral lunacy.\"\n" +
                "— WR Inge");
        listOfQuotes.add("\"It is easy to take liberty for granted when you have never had it taken from you.\"\n" +
                "— Dick Cheney");
        listOfQuotes.add("\"A man's feet must be planted in his country, but his eyes should survey the world.\"\n" +
                "— George Santayana");
        listOfQuotes.add("\"One good act of vengeance deserves another.\"\n" +
                "— Jon Jefferson");
        listOfQuotes.add("\"You cannot get ahead while you are getting even.\"\n" +
                "— Dick Armey");
        listOfQuotes.add("\"A nation reveals itself not only by the men it produces but also by the men it honors, the men it remembers.\"\n" +
                "— John F. Kennedy");
        listOfQuotes.add("\"Patriotism is an arbitrary veneration of real estate above principles.\"\n" +
                "— George Jean Nathan");
        listOfQuotes.add("\"Soldiers usually win the battles and generals get the credit for them\"\n" +
                "— Napoleon Bonaparte");
        listOfQuotes.add("\"War is fear cloaked in courage.\"\n" +
                "— General William C. Westmoreland");
        listOfQuotes.add("\"In peace, sons bury their fathers. In war, fathers bury their sons.\"\n" +
                "— Herodotus");
        listOfQuotes.add("\"Don't get mad, get even.\"\n" +
                "— John F. Kennedy");
        listOfQuotes.add("\"A man who would not risk his life for something does not deserve to live.\"\n" +
                "— Martin Luther King");
        listOfQuotes.add("\"The future influences the present just as much as the past.\"\n" +
                "— Friedrich Nietzsche");
        listOfQuotes.add("\"You cannot escape the responsibility of tomorrow by evading it today.\"\n" +
                "— Abraham Lincoln");
        listOfQuotes.add("\"Study the past if you would define the future.\"\n" +
                "— Confucius");
        listOfQuotes.add("\"Do your duty a little more and the future will take care of itself.\"\n" +
                "— Andrew Carnegie");


        final TypeWriter tvQuote = findViewById(R.id.tv_quote);
        nextQuote = findViewById(R.id.btn_next);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.decoding_sound);
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        nextQuote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Fade animation
//                TransitionManager.beginDelayedTransition(transitionsContainer,
//                        new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN));
//                tvQuote.setText(listOfQuotes.get(selector));

                // Set decoding sound on click
                mp.start();
//                vibrator.vibrate(100);

                // Typewriter animation
                Random randomNumber = new Random();
                selector = randomNumber.nextInt(176);
                tvQuote.setText("");
                tvQuote.setCharacterDelay(30);
                tvQuote.animateText(listOfQuotes.get(selector));

            }
        });

    }

    // Bind/Unbind music service
    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this, MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Detect idle screen
        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //UNBIND music service
        doUnbindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        stopService(music);

    }

}

