import { useThemeMode, ThemeMode } from 'flowbite-react';

function useThemeToggler() {
  const { setMode } = useThemeMode();
  const toggleTheme = () => {
    let storedTheme = localStorage.getItem('theme');
    const newTheme = storedTheme === 'dark' ? 'light' : 'dark';
    localStorage.setItem('theme', newTheme);
    setMode(newTheme as ThemeMode);
  };

  return { toggleTheme }; // You can return other values if needed, like the current mode
}

export default useThemeToggler;
